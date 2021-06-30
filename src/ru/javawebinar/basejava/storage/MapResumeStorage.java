package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MapResumeStorage extends AbstractStorage<Resume> {

    private final Map<String, Resume> storage = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void doSave(Resume r, Resume resume) {
        storage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(Resume resume) {
        storage.remove(resume.getUuid());
    }

    @Override
    protected void doUpdate(Resume r, Resume resume) {
        storage.replace(r.getUuid(), r);
    }

    @Override
    protected Resume doGet(Resume resume) {
        return resume;
    }

    @Override
    protected boolean isExist(Resume resume) {
        return resume != null;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

}
