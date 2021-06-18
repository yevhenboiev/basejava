package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > -1) {
            throw new ExistStorageException(r.getUuid());
        }
        doSave(r, index);
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < -1) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(index);
    }

    public void clear() {
        clearElement();
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(r, index);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < -1) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(index);
    }

    public int size() {
        return getSize();
    }

    public Resume[] getAll() { return doGetAll(); }

    protected abstract void doSave(Resume r, int index);

    protected abstract void doDelete(int index);

    protected abstract void clearElement();

    protected abstract void doUpdate(Resume r, int index);

    protected abstract Resume doGet(int index);

    protected abstract int getSize();

    protected abstract Resume[] doGetAll();

    protected abstract int getIndex(String uuid);
}
