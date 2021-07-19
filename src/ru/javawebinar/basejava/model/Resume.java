package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;

    private String uuid;
    private String fullName;
    private Map<ContactsType, String> contacts = new EnumMap<>(ContactsType.class);
    private Map<SectionType, AbstractSection> section = new EnumMap<>(SectionType.class);

    public Resume() {
    }

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.fullName = fullName;
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getContacts(ContactsType type) {
        return contacts.get(type);
    }

    public void setContacts(ContactsType type, String contact) {
        contacts.put(type, contact);
    }

    public AbstractSection getSection(SectionType type) {
        return section.get(type);
    }

    public void setSection(SectionType type, AbstractSection content) {
        section.put(type, content);
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return uuid.equals(resume.uuid) && fullName.equals(resume.fullName) && contacts.equals(resume.contacts)
                && section.equals(resume.section);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, fullName, contacts, section);
    }

    @Override
    public String toString() {
        return "UUID: " + uuid + "\nFullName: " + fullName;
    }

    @Override
    public int compareTo(Resume o) {
        int result = fullName.compareTo(o.fullName);
        return result != 0 ? result : uuid.compareTo(o.uuid);
    }


}