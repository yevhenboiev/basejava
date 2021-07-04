package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.Objects;

public class EducationSection extends DataType {

    private final String nameCourse;

    public EducationSection(String organization, LocalDate startDate, LocalDate overDate, String nameCourse) {
        super(organization, startDate, overDate);
        this.nameCourse = nameCourse;
    }

    public String getNameCourse() {
        return nameCourse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EducationSection that = (EducationSection) o;
        return nameCourse.equals(that.nameCourse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nameCourse);
    }

    @Override
    public String toString() {
        return "\t\t" + nameCourse;
    }
}
