package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        Object getSearchKey = getNotExistSearchKey(r.getUuid());
        doSave(r, getSearchKey);
    }

    public void delete(String uuid) {
        Object getSearchKey = getExistSearchKey(uuid);
        doDelete(getSearchKey);
    }

    public void update(Resume r) {
        Object getSearchKey = getExistSearchKey(r.getUuid());
        doUpdate(r, getSearchKey);
    }

    public Resume get(String uuid) {
        Object getSearchKey = getExistSearchKey(uuid);
        return doGet(getSearchKey);
    }

    protected abstract void doSave(Resume r, Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract void doUpdate(Resume r, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isExist(Object searchKey);

    protected Object getExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object getNotExistSearchKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
