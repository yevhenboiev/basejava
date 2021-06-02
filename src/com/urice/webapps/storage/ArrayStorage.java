package com.urice.webapps.storage;

import com.urice.webapps.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] STORAGE = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(STORAGE, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (findIndex(r.getUuid()) != -1) {
            System.out.println("Резюме " + r.getUuid() + " уже существует");
        } else if (size >= STORAGE.length) {
            System.out.println("База резме уже переполнена");
        } else {
            STORAGE[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме " + uuid + " не существует");
            return null;
        }
        return STORAGE[index];
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index == -1) {
            System.out.println("Данного резмюе не существует");
        } else {
            STORAGE[index] = r;
        }
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index == -1) {
            System.out.println("Данное резюме отсутсвует!");
        } else {
            STORAGE[index] = STORAGE[size - 1];
            STORAGE[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(STORAGE, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(STORAGE[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
