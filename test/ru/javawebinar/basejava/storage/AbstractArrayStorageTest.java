package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private final Resume r1 = new Resume("uuid1");
    private final Resume r2 = new Resume("uuid2");
    private final Resume r3 = new Resume("uuid3");

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() {
        storage.update(r1);
        assertEquals(r1, storage.get(r1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r4 = new Resume();
        storage.update(r4);
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        assertEquals(r4, storage.get(r4.getUuid()));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                Resume R_i = new Resume();
                storage.save(R_i);
            }
        } catch (StorageException exp) {
            fail("Переполнение произошло раньше времени");
        }
        Resume R_10000 = new Resume();
        storage.save(R_10000);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(r1.getUuid());
        assertNotEquals(r1, storage.get(r1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.get("room");
    }

    @Test
    public void getAll() {
        Resume[] array = {r1, r2, r3};
        Resume[] allResume = storage.getAll();
        assertEquals(array[0], allResume[0]);
        assertEquals(array[1], allResume[1]);
        assertEquals(array[2], allResume[2]);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Resume[] array = {storage.get(r1.getUuid())};
        assertEquals(array[0], storage.get(r1.getUuid()));
    }
}