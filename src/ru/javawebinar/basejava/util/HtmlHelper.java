package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.List;

public class HtmlHelper {

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
                return type.getTitle() + " : " +  toLink("skype: " + value, value);
            case MAIL:
                return type.getTitle() + " : " + toLink("mailto: " + value, value);
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
                    return edit ? ((TextSection) value).getContent() : "<p>" + ((TextSection) value).getContent() + "</p>";
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
            sb.append("<li>").append(line).append("</li>");
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
        organizationList.forEach(organization -> {
            sb.append("<div class=\"education\">")
                    .append(OPEN_H3)
                    .append(toLink(organization.getHomePage().getUrl(), organization.getHomePage().getName()))
                    .append(CLOSE_H3);
            organization.getPositions().forEach(position -> sb.append(getPosition(position)));
            sb.append("</div>");
        });
        return sb.toString();
    }

    public static String getPosition(Organization.Position position) {
        StringBuilder sb = new StringBuilder();
        LocalDate startDate = position.getStartDate();
        LocalDate endDate = position.getEndDate();
        sb.append("<div class=\"date_and_title\">")
                .append("<div class=\"time\">")
                .append("<h4>")
                .append(startDate.getMonthValue()).append("/").append(startDate.getYear())
                .append(" - ")
                .append(endDate.getYear() == 3000 ? "Now" : endDate.getMonthValue() + "/" + endDate.getYear())
                .append("</h4>")
                .append("</div>")
                .append("<div class = title_and_description>")
                .append(OPEN_B)
                .append(position.getTitle())
                .append(CLOSE_B)
                .append(BR)
                .append("<p>")
                .append(position.getDescription())
                .append("</p>").append("</div>").append("</div>");
        return sb.toString();
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }

    public static String deleteDoubleQuotes(String title) {
        return String.join("\"", title);
    }
}
