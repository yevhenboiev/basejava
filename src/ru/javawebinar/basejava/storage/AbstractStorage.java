package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        Object getSearchKey = ExistSearchKey(r.getUuid());
        doSave(r, getSearchKey);
    }

    public void delete(String uuid) {
        Object getSearchKey = NotExistSearchKey(uuid);
        doDelete(getSearchKey);
    }

    public void update(Resume r) {
        Object getSearchKey = NotExistSearchKey(r.getUuid());
        doUpdate(r, getSearchKey);
    }

    public Resume get(String uuid) {
        Object getSearchKey = NotExistSearchKey(uuid);
        return doGet(getSearchKey);
    }

    protected abstract void doSave(Resume r, Object index);

    protected abstract void doDelete(Object index);

    protected abstract void doUpdate(Resume r, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract Object getIndex(String uuid);

    protected abstract boolean isExist(Object index);

    protected Object NotExistSearchKey(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected Object ExistSearchKey(String uuid) {
        Object searchKey = getIndex(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
