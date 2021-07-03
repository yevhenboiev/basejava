package ru.javawebinar.basejava.model;

import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> contentList;

    public ListSection(List<String> contentList) {
        this.contentList = contentList;
    }

    public List<String> getContentList() {
        return contentList;
    }

    @Override
    public String toString() {
        return contentList.toString();
    }
}
