package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.List;

public class HtmlHelper {

    public static final String OPEN_TR = "<tr>";
    public static final String CLOSE_TR = "</tr>";
    public static final String OPEN_TD = "<td>";
    public static final String CLOSE_TD = "</td>";
    public static final String OPEN_H3 = "<h3>";
    public static final String CLOSE_H3 = "</h3>";
    public static final String OPEN_B = "<b>";
    public static final String CLOSE_B = "</b>";
    public static final String BR = "<br/>";


    public static String toHtmlContactType(ContactsType type, String value) {
        value = value == null ? "" : value;
        switch (type) {
            case PHONE_NUMBER:
            case HOME_PHONE:
                return type.getTitle() + " : " + value;
            case SKYPE:
                return toLink("skype: " + value, type.getTitle());
            case MAIL:
                return toLink("mailto: " + value, type.getTitle());
            case LINKEDIN:
            case GIT_HUB:
            case STACKOVERFLOW:
            case HOME_PAGE:
                return toLink(value, type.getTitle());
        }
        return null;
    }

    public static String toHtmlSectionType(SectionType type, AbstractSection value, boolean edit) {
        if (value != null) {
            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    return ((TextSection) value).getContent();
                case ACHIEVEMENT:
                case QUALIFICATION:
                    return edit ? getListToEdit((ListSection) value) : getListToView((ListSection) value);
                case EDUCATION:
                case EXPERIENCE:
                    return getOrganization((OrganizationSection) value);
            }
        }
        return "";
    }

    private static String getListToView(ListSection list) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        for (String line : list.getContentList()) {
            if (!line.equals("\r")) {
                sb.append("<li>").append(line).append("</li>");
            }
        }
        sb.append("</ul>");
        return sb.toString();
    }

    private static String getListToEdit(ListSection list) {
        return String.join("\n", list.getContentList());
    }

    private static String getOrganization(OrganizationSection value) {
        List<Organization> organizationList = value.getOrganizations();
        StringBuilder sb = new StringBuilder();
        sb.append("<table>");
        organizationList.forEach(organization -> {
                sb.append(OPEN_TR).append(OPEN_TD).append(OPEN_H3)
                        .append(toLink(organization.getHomePage().getUrl(), organization.getHomePage().getName()))
                        .append(CLOSE_H3)
                        .append(CLOSE_TD)
                        .append(CLOSE_TR);
        organization.getPositions().forEach(position -> sb.append(getPosition(position)));
                        sb.append("<table>");
    });
        return sb.toString();
}

    public static String getPosition(Organization.Position position) {
        StringBuilder sb = new StringBuilder();
        LocalDate startDate = position.getStartDate();
        LocalDate endDate = position.getEndDate();
        sb.append(OPEN_TR).append(OPEN_TD)
                .append(startDate.getMonthValue()).append("/").append(startDate.getYear())
                .append(" - ")
                .append(endDate.getYear() == 3000 ? "Now" : endDate.getMonthValue() + "/" + endDate.getYear())
                .append(CLOSE_TD)
                .append(OPEN_TD)
                .append(OPEN_B)
                .append(position.getTitle())
                .append(CLOSE_B)
                .append(BR)
                .append(position.getDescription())
                .append(CLOSE_TD).append(CLOSE_TR);
        return sb.toString();
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

    public static String deleteDoubleQuotes(String title) {
        return String.join("\"", title);
    }
}
