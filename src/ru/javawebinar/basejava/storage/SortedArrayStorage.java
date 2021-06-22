package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveElement(Resume r, Object index) {
        int sortIndex = -(int) index - 1;
        System.arraycopy(storage, sortIndex, storage, sortIndex + 1, size - sortIndex);
        storage[sortIndex] = r;
    }

    @Override
    protected void deletedElement(Object index) {
        int deleteIndex = (int) index - 1;
        System.arraycopy(storage, (int) index + 1, storage, (int) index, size - deleteIndex);
    }

    @Override
    protected Integer getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
