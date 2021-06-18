package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected void doSave(Resume r, int index) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            saveElement(r, index);
            size++;
        }
    }

    protected void doDelete(int index) {
        deletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    protected void clearElement() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void doUpdate(Resume r, int index) {
        storage[index] = r;
    }

    protected Resume doGet(int index) {
        return storage[index];
    }

    protected int getSize() {
        return size;
    }

    protected Resume[] doGetAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void saveElement(Resume r, int index);

    protected abstract void deletedElement(int index);
}
