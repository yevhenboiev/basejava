package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Organization extends AbstractSection {
    private final Link organization;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yyyy");

    public Organization(String name, String url, LocalDate startDate, LocalDate endDate) {
        Objects.requireNonNull(startDate, "startDate must not by null");
        Objects.requireNonNull(endDate, "endDate must not by null");
        this.organization = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization organization = (Organization) o;
        return this.organization.equals(organization.organization) && startDate.equals(organization.startDate) && endDate.equals(organization.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization, startDate, endDate);
    }

    @Override
    public String toString() {
        return "\t" + organization + "\n" + "\t" + startDate.toString() + " - " + endDate.toString();
    }
}
