package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactsType, String> contacts = r.getContacts();
            writeWithException(dos, contacts.entrySet(), writer -> {
                dos.writeUTF(writer.getKey().name());
                dos.writeUTF(writer.getValue());
            });

            Map<SectionType, AbstractSection> section = r.getSection();
            writeWithException(dos, section.entrySet(), writer -> {
                SectionType type = writer.getKey();
                AbstractSection sections = writer.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) sections).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        writeWithException(dos, ((ListSection) sections).getContentList(), dos::writeUTF);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeWithException(dos, ((OrganizationSection) sections).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl());
                            writeWithException(dos, organization.getPositions(), position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readWithException(dis, () -> resume.setContacts(ContactsType.valueOf(dis.readUTF()), dis.readUTF()));

            readWithException(dis, () -> {
                String sectionName = dis.readUTF();
                switch (SectionType.valueOf(sectionName)) {
                    case OBJECTIVE:
                    case PERSONAL:
                        TextSection personalSection = new TextSection(dis.readUTF());
                        resume.setSection(SectionType.valueOf(sectionName), personalSection);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        List<String> list = new ArrayList<>();
                        readWithException(dis, () -> list.add(dis.readUTF()));
                        ListSection listSection = new ListSection(list);
                        resume.setSection(SectionType.valueOf(sectionName), listSection);
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Organization> listOrganization = new ArrayList<>();
                        List<Organization.Position> listOrganizationPosition = new ArrayList<>();
                        readWithException(dis, () -> {
                            Link link = new Link(dis.readUTF(), dis.readUTF());
                            readWithException(dis, () -> {
                                Organization.Position position = new Organization.Position(
                                        LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF());
                                listOrganizationPosition.add(position);
                            });
                            Organization organization = new Organization(link, listOrganizationPosition);
                            listOrganization.add(organization);
                        });
                        OrganizationSection organizationSection = new OrganizationSection(listOrganization);
                        resume.setSection(SectionType.valueOf(sectionName), organizationSection);
                        break;
                }
            });
            return resume;
        }
    }

    @FunctionalInterface
    interface DataWriter<T> {
        void write(T t) throws IOException;
    }

    public <T> void writeWithException(DataOutputStream dos, Collection<T> collection, DataWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T entry : collection) {
            writer.write(entry);
        }
    }

    @FunctionalInterface
    interface DataReader {
        void read() throws IOException;
    }

    public void readWithException(DataInputStream dis, DataReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}

