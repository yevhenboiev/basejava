package ru.javawebinar.basejava.storage.serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
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
                        ListSection achievement = (ListSection) entry.getValue();
                        dos.writeInt(achievement.getContentList().size());
                        for (String s : achievement.getContentList()) {
                            dos.writeUTF(s);
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

            for (int i = 0; i < dis.readInt(); i++) {
                String sectionName = dis.readUTF();
                switch (sectionName) {
                    case "OBJECTIVE":
                    case "PERSONAL":
                        TextSection personalSection = new TextSection(sectionName);
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
                }
            }
            return resume;
        }
    }
}

