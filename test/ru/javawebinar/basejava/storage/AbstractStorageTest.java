package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    private final Resume r1 = new Resume("uuid1");
    private final Resume r2 = new Resume("uuid2");
    private final Resume r3 = new Resume("uuid3");

    protected AbstractStorageTest(Storage storage) {
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
        storage.update(new Resume());
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

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(r1.getUuid());
        assertEquals(2, storage.size());
        assertNotEquals(r1, storage.get(r1.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("uuid5");
    }

    @Test
    public void getAll() {
        Resume[] expectedResumes = {r1, r2, r3};
        Resume[] actualResumes = storage.getAll();
        assertEquals(expectedResumes.length, actualResumes.length);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void get() {
        assertEquals(r1, storage.get(r1.getUuid()));
    }
}