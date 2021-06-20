package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    public List<Resume> storage = new ArrayList<>();

    @Override
    protected void doSave(Resume r, int index) {
        storage.add(r);
    }

    @Override
    protected void doDelete(int index) {
        storage.remove(index);
    }

    public void clear() {
        storage.removeAll(storage);
    }

    @Override
    protected void doUpdate(Resume r, int index) {
        storage.set(index, r);
    }

    @Override
    protected Resume doGet(int index) {
        return storage.get(index);
    }

    public int size() {
        return storage.size();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}

