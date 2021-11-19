package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.model.*;

public class HtmlHelper {

    public static String toHtmlContactType(ContactsType type, String value) {
        value = value == null ? "" : value;
        switch (type) {
            case PHONE_NUMBER:
            case HOME_PHONE:
                return type.getTitle() + " : " + value;
            case SKYPE:
                return toLink("skype: " + value, type.getTitle());
            case MAIL:
                return type.getTitle() + ": " + toLink("mailto: " + value, type.getTitle());
            case LINKEDIN:
            case GIT_HUB:
            case STACKOVERFLOW:
            case HOME_PAGE:
                return toLink(value, type.getTitle());
        }
        return null;
    }

    public static String toHtmlSectionType(SectionType type, AbstractSection value) {
        String title = "<h3>" + type.getTitle() + "</h3>";
        if (value != null) {
            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    TextSection textSection = (TextSection) value;
                    return textSection.getContent().equals("") ? "" : title + textSection.getContent() + "<hr>";
                case ACHIEVEMENT:
                case QUALIFICATION:
                    ListSection listSection = (ListSection) value;

                    return listSection.getContentList().isEmpty() ? "" :
                            title + String.join("<br/>", listSection.getContentList()) + "<hr>";
            }
        }
        return "";
    }

    public static String toLink(String href, String title) {
        return "<a href='" + href + "'>" + title + "</a>";
    }
}
