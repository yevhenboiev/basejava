package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void save(Resume r) {
        Object index = getIndex(r.getUuid());
        if (isExist(index)) {
         throw new ExistStorageException(r.getUuid());
        }
        doSave(r, index);
    }

    public void delete(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        doDelete(index);
    }

    public void update(Resume r) {
        Object index = getIndex(r.getUuid());
        if (!isExist(index)) {
            throw new NotExistStorageException(r.getUuid());
        }
        doUpdate(r, index);
    }

    public Resume get(String uuid) {
        Object index = getIndex(uuid);
        if (!isExist(index)) {
            throw new NotExistStorageException(uuid);
        }
        return doGet(index);
    }

    protected abstract void doSave(Resume r, Object index);

    protected abstract void doDelete(Object index);

    protected abstract void doUpdate(Resume r, Object index);

    protected abstract Resume doGet(Object index);

    protected abstract Object getIndex(String uuid);

    protected abstract boolean isExist(Object getIndex);
}
