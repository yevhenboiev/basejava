package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
        Resume r1 = new Resume("uuid1");
        storage.update(r1);
        Resume[] array = storage.getAll();
        assertEquals(array[0], r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        Resume r1 = new Resume();
        storage.update(r1);
        Resume[] array = storage.getAll();
        assertEquals(array[0], r1);
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(r1);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        for (int i = 0; i < 10000; i++) {
            Resume R_i = new Resume();
            storage.save(R_i);
        }
    }

    @Test
    public void delete() {
        storage.delete(r1.getUuid());
        Resume[] array = storage.getAll();
        assertEquals(2, array.length);
        assertNotEquals(array[0], r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.get("room");
    }

    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        assertEquals(array[0], r1);
        assertEquals(array[1], r2);
        assertEquals(array[2], r3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        Resume[] array = storage.getAll();
        assertEquals(array[0], storage.get(r1.getUuid()));
    }
}