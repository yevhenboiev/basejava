package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveElement(Resume r, int searchKey) {
        int sortIndex = -searchKey - 1;
        System.arraycopy(storage, sortIndex, storage, sortIndex + 1, size - sortIndex);
        storage[sortIndex] = r;
    }

    @Override
    protected void deletedElement(int searchKey) {
        int deleteIndex = searchKey - 1;
        System.arraycopy(storage, searchKey + 1, storage, searchKey, size - deleteIndex);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
