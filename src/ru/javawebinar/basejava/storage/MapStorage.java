package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void doSave(Resume r, String uuid) {
        storage.put(uuid, r);
    }

    @Override
    protected void doDelete(String uuid) {
        storage.remove(uuid);
    }

    @Override
    protected void doUpdate(Resume r, String uuid) {
        storage.replace(uuid, r);
    }

    @Override
    protected Resume doGet(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected boolean isExist(String uuid) {
        return storage.containsKey(uuid);
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
}