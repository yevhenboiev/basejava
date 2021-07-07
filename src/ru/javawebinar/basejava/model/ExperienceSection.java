package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class ExperienceSection extends Organization {

    private final String position;
    private final String additionalInformation;

    public ExperienceSection(String name, String url, LocalDate startDate, LocalDate overDate, String position, String additionalInformation) {
        super(name, url, startDate, overDate);
        Objects.requireNonNull(position, "position must not by null");
        Objects.requireNonNull(additionalInformation, "additionalInformation must not by null");
        this.position = position;
        this.additionalInformation = additionalInformation;
    }


    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ExperienceSection that = (ExperienceSection) o;
        return position.equals(that.position) && additionalInformation.equals(that.additionalInformation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position, additionalInformation);
    }

    @Override
    public String toString() {
        return "\t\t" + position + "\t\t" + additionalInformation;
    }
}
