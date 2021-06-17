package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage {
    protected static List<Resume> storage = new ArrayList<>();

    public void save(Resume r) {
        storage.add(r);
    }

    public void deleted(String uuid) {
        int index = getIndex(uuid);
        storage.remove(index);
    }

    public void clear() {
        storage.removeAll(storage);
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        storage.set(index, r);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        return storage.get(index);
    }

    public void getAll() {
        for (Resume r: storage) {
            System.out.println(r);
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (uuid.equals(storage.get(i).getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
