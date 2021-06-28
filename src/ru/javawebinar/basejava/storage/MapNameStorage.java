package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapNameStorage extends AbstractStorage implements Comparator<Resume> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object searchKey) {
        storage.put((String) searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected void doUpdate(Resume r, Object searchKey) {
        storage.replace((String) searchKey, r);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected String getSearchKey(String fullName) {
        return fullName;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public int compare(Resume o1, Resume o2) {
        int result;
        result = o1.getFullName().compareTo(o2.getFullName());
        if (result == 0) {
            result = o1.getUuid().compareTo(o2.getUuid());
        }
        return result;
    }
}
