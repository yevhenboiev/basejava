package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage<SK> implements Storage {

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    public void save(Resume r) {
        SK getSearchKey = getNotExistSearchKey(r.getUuid());
        doSave(r, getSearchKey);
    }

    public void delete(String uuid) {
        SK getSearchKey = getExistSearchKey(uuid);
        doDelete(getSearchKey);
    }

    public void update(Resume r) {
        SK getSearchKey = getExistSearchKey(r.getUuid());
        doUpdate(r, getSearchKey);
    }

    public Resume get(String uuid) {
        SK getSearchKey = getExistSearchKey(uuid);
        return doGet(getSearchKey);
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
