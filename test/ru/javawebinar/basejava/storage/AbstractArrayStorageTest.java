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
    public void clear() throws Exception {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {


    }

    @Test
    public void save() throws Exception {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        assertEquals(4, storage.size());
    }

    @Test
    public void delete() throws Exception {
        storage.delete(r1.getUuid());
        Resume[] array = storage.getAll();
        assertEquals(2, array.length);
        assertNotEquals(array[0], r1);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(array[0], r1);
        assertEquals(array[1], r2);
        assertEquals(array[2], r3);
    }

    @Test
    public void size() throws Exception {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() throws Exception {
        storage.get(r1.getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(r1);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = StorageException.class)
    public void getOverflow() throws Exception {
        for (int i = 0; i < 9999; i++) {
            new Resume();
        }
    }
}