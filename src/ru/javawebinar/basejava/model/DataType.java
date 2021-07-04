package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DataType extends AbstractSection {
    private final String organization;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yyyy");

    public DataType(String organization, LocalDate startDate, LocalDate endDate) {
        this.organization = organization;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataType dataType = (DataType) o;
        return organization.equals(dataType.organization) && startDate.equals(dataType.startDate) && endDate.equals(dataType.endDate);
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
