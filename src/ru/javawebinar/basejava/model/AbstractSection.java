package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
abstract public class AbstractSection implements Serializable {
    private static final long serialVersionUID = 1L;

    public List<Organization> getOrganizations() {
        return getOrganizations();
    }

}
