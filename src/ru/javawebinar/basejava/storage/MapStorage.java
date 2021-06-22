package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected void doSave(Resume r, Object index) {
        storage.put((String) index, r);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove(index);
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.replace((String) index, r);
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get(index);
    }

    @Override
    protected String getIndex(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(Object getIndex) {
        return storage.containsKey(getIndex);
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

}
