package com.urice.webapps.storage;

import com.urice.webapps.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (checkResume(r.getUuid()) != -1) {
            System.out.println("Данное резюме уже существет");
        } else if (size >= storage.length) {
            System.out.println("База резме уже переполнена");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = checkResume(uuid);
        if (index == -1) {
            System.out.println("Данного резюме не существует");
            return null;
        } else {
            return storage[index];
        }
    }

    public void update(Resume r) {
        int index = checkResume(r.getUuid());
        if(index == -1) {
            System.out.println("Данного резмюе не существует");
        } else {
            storage[index] = r;
        }
    }

    public void delete(String uuid) {
        int index = checkResume(uuid);
        if (index == -1) {
            System.out.println("Данное резюме отсутсвует!");
        } else {
            storage[index] = storage[size -1];
            storage[size -1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int checkResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
