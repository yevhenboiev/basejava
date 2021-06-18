package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected void saveElement(Resume r, int index) {
        int sortIndex = -index - 1;
        System.arraycopy(storage, sortIndex, storage, sortIndex + 1, size - sortIndex);
        storage[sortIndex] = r;
        size++;
    }

    @Override
    protected void deletedElement(int index) {
        int deleteIndex = index - 1;
        System.arraycopy(storage, index + 1, storage, index, size - deleteIndex);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
