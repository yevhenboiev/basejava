package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends Section {
    private final List<String> contentList;

    public ListSection(List<String> contentList) {
        Objects.requireNonNull(contentList, "content list must not be null");
        this.contentList = contentList;
    }

    public List<String> getContentList() {
        return contentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return contentList.equals(that.contentList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contentList);
    }

    @Override
    public String toString() {
        return "\t" + contentList.toString();
    }
}
