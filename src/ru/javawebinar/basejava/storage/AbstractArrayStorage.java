package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void doSave(Resume r, Object index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        }
        saveElement(r, index);
        size++;
    }

    protected void doDelete(Object index) {
        deletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void doUpdate(Resume r, Object index) {
        storage[(int) index] = r;
    }

    protected Resume doGet(Object index) {
        return storage[(int) index];
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract Integer getIndex(String uuid);

    protected abstract void saveElement(Resume r, Object index);

    protected abstract void deletedElement(Object index);

    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
