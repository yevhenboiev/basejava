package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(Resume r, Object index) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object index) {
        storage.remove((int) index);
    }

    public void clear() {
        storage.removeAll(storage);
    }

    @Override
    protected void doUpdate(Resume r, Object index) {
        storage.set((int) index, r);
    }

    @Override
    protected Resume doGet(Object index) {
        return storage.get((int) index);
    }

    public int size() {
        return storage.size();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected Integer getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return null;
    }

    @Override
    protected boolean isExist(Object getIndex) {
        return getIndex != null;
    }
}

