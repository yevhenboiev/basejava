package ru.javawebinar.basejava.model;

public enum ContactsType {
    PHONE_NUMBER("Номер телефона"),
    HOME_PHONE("Домашний телефон"),
    SKYPE("Skype"),
    MAIL("Mail"),
    LINKEDIN("Профиль LinkedIn"),
    GIT_HUB("Профиль Git Hub"),
    STACKOVERFLOW("Профиль StackOverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
