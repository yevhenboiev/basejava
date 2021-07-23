package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactsType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactsType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, AbstractSection> section = r.getSection();
            dos.writeInt(section.size());
            for (Map.Entry<SectionType, AbstractSection> entry : section.entrySet()) {
                switch (entry.getKey().name()) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        dos.writeUTF(entry.getKey().name());
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATION":
                        dos.writeUTF(entry.getKey().name());
                        ListSection listSection = (ListSection) entry.getValue();
                        dos.writeInt(listSection.getContentList().size());
                        for (String s : listSection.getContentList()) {
                            dos.writeUTF(s);
                        }
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION":
                        dos.writeUTF(entry.getKey().name());
                        OrganizationSection organizationSection = (OrganizationSection) entry.getValue();
                        List<Organization> organizations = organizationSection.getOrganizations();
                        int sizeOrganizations = organizations.size();
                        dos.writeInt(sizeOrganizations);
                        for (Organization organization : organizations) {
                            List<Organization.Position> positions = organization.getPositions();
                            int sizePosition = positions.size();
                            dos.writeInt(sizePosition);
                            for (Organization.Position position : positions) {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getDescription() != null ? position.getDescription() : "null");
                            }
                            dos.writeUTF(organization.getHomePage().getName());
                            dos.writeUTF(organization.getHomePage().getUrl() != null ? organization.getHomePage().getUrl() : "null");
                        }
                        break;
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContact = dis.readInt();
            for (int i = 0; i < sizeContact; i++) {
                resume.setContacts(ContactsType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSection = dis.readInt();
            for (int i = 0; i < sizeSection; i++) {
                String sectionName = dis.readUTF();
                switch (sectionName) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        TextSection personalSection = new TextSection(dis.readUTF());
                        resume.setSection(SectionType.valueOf(sectionName), personalSection);
                        break;
                    case "ACHIEVEMENT":
                    case "QUALIFICATION":
                        List<String> list = new ArrayList<>();
                        int sizeList = dis.readInt();
                        for (int j = 0; j < sizeList; j++) {
                            list.add(dis.readUTF());
                        }
                        ListSection listSection = new ListSection(list);
                        resume.setSection(SectionType.valueOf(sectionName), listSection);
                        break;
                    case "EXPERIENCE":
                    case "EDUCATION" :
                        List<Organization> listOrganization = new ArrayList<>();
                        List<Organization.Position> listOrganizationPosition = new ArrayList<>();
                        int sizeOrganization = dis.readInt();
                        int sizePosition = dis.readInt();
                        for (int o = 0; o < sizeOrganization; o++) {
                            for (int k = 0; k < sizePosition; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endData = LocalDate.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String description = dis.readUTF();
                                if (description.equals("null")) {
                                    description = null;
                                }
                                Organization.Position position = new Organization.Position(
                                        startDate, endData, title, description);
                                listOrganizationPosition.add(position);
                            }
                            String name = dis.readUTF();
                            String url = dis.readUTF();
                            if (url.equals("null")) {
                                url = null;
                            }
                            Link link = new Link(name, url);
                            Organization organization = new Organization(link, listOrganizationPosition);
                            listOrganization.add(organization);
                        }
                        OrganizationSection organizationSection = new OrganizationSection(listOrganization);
                        resume.setSection(SectionType.valueOf(sectionName), organizationSection);
                        break;
                }
            }
            return resume;
        }
    }
}

