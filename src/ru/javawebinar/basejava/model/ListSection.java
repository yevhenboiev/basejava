package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;

    private List<String> contentList;

    public ListSection() {
    }

    public ListSection(String... contentList) {
        this(Arrays.asList(contentList));
    }

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
        return String.join("/n", contentList);
    }
}
