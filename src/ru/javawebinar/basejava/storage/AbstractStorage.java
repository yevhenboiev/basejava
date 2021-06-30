package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected abstract SK getSearchKey(String uuid);

    protected abstract void doSave(Resume r, SK searchKey);

    protected abstract void doDelete(SK searchKey);

    protected abstract void doUpdate(Resume r, SK searchKey);

    protected abstract Resume doGet(SK searchKey);

    protected abstract boolean isExist(SK searchKey);

    public void save(Resume r) {
        LOG.info("SAVE " + r);
        SK getSearchKey = getNotExistSearchKey(r.getUuid());
        doSave(r, getSearchKey);
    }

    public void delete(String uuid) {
        LOG.info("DELETE " + uuid);
        SK getSearchKey = getExistSearchKey(uuid);
        doDelete(getSearchKey);
    }

    public void update(Resume r) {
        LOG.info("UPDATE " + r);
        SK getSearchKey = getExistSearchKey(r.getUuid());
        doUpdate(r, getSearchKey);
    }

    public Resume get(String uuid) {
        LOG.info("GET " + uuid);
        SK getSearchKey = getExistSearchKey(uuid);
        return doGet(getSearchKey);
    }

    private SK getExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " not exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Resume " + uuid + " already exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }
}
