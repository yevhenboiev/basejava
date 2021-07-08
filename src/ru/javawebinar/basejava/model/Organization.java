package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Organization {
    private final Link homePage;
    private final List<TimeZoneOrganization> timeZone;

    public Organization(String name, String url, List<TimeZoneOrganization> timeZone) {
        this.homePage = new Link(name, url);
        this.timeZone = timeZone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return homePage.equals(that.homePage) && timeZone.equals(that.timeZone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, timeZone);
    }

    @Override
    public String toString() {
        return "Organization{" +
                "homePage=" + homePage +
                ", timeZone=" + timeZone +
                '}';
    }
}
