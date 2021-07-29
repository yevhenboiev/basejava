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
                            Link link = organization.getHomePage();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl());
                            writeWithException(dos, organization.getPositions(), position -> {
                                writeLocalData(dos, position.getStartDate());
                                writeLocalData(dos, position.getEndDate());
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
                        resume.setSection(SectionType.valueOf(sectionName), new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATION:
                        resume.setSection(SectionType.valueOf(sectionName), new ListSection(readListWithException(dis, dis::readUTF)));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        resume.setSection(SectionType.valueOf(sectionName), new OrganizationSection(readListWithException(dis, () ->
                                new Organization(new Link(dis.readUTF(), dis.readUTF()), readListWithException(dis, () ->
                                new Organization.Position(readLocalDate(dis.readInt(), dis.readInt()),
                                        readLocalDate(dis.readInt(), dis.readInt()),dis.readUTF(), dis.readUTF()))))));
                        break;
                }
            });
            return resume;
        }
    }

    private void writeLocalData(DataOutputStream dos, LocalDate data) throws IOException {
        dos.writeInt(data.getYear());
        dos.writeInt(data.getMonthValue());
    }

    private LocalDate readLocalDate(int year, int monthValue) {
        return LocalDate.of(year, monthValue, 1);
    }

    @FunctionalInterface
    interface DataWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, DataWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T entry : collection) {
            writer.write(entry);
        }
    }

    @FunctionalInterface
    interface DataReader {
        void read() throws IOException;
    }

    private void readWithException(DataInputStream dis, DataReader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    @FunctionalInterface
    interface ListReader<T> {
        T readList() throws IOException;
    }

    private <T> List<T> readListWithException(DataInputStream dis, ListReader<T> list) throws IOException{
        List<T> readerList = new ArrayList<>();
        int size = dis.readInt();
        for(int i = 0; i < size; i ++) {
            readerList.add(list.readList());
        }
        return readerList;
    }
}

