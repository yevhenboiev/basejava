package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void doSave(Resume r, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveElement(r, (Integer) searchKey);
        size++;
    }

    protected void doDelete(Object searchKey) {
        deletedElement((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void doUpdate(Resume r, Object searchKey) {
        storage[(Integer) searchKey] = r;
    }

    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    public int size() {
        return size;
    }

    @Override
    public List<Resume> getAllSorted() {
        Resume[] copy = Arrays.copyOfRange(storage, 0, size);
        return new ArrayList<>(Arrays.asList(copy));
    }

    protected abstract Integer getSearchKey(String uuid);

    protected abstract void saveElement(Resume r, int searchKey);

    protected abstract void deletedElement(int searchKey);

    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }
}