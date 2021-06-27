package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public abstract class AbstractStorageTest {
    protected final Storage storage;
    private final Resume r1 = new Resume("uuid1", "Pypok Igor");
    private final Resume r2 = new Resume("uuid2", "Lomak Oleg");
    private final Resume r3 = new Resume("uuid3", "Roman Ytkin");

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
        storage.update(new Resume("uuid5", "Dijon Valiev"));
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4", "Dijon Valiev");
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
        storage.delete("Dummy");
    }

    @Test
    public void getAll() {
        List<Resume> expectedResumes = new ArrayList<>();
        expectedResumes.add(r1);
        expectedResumes.add(r2);
        expectedResumes.add(r3);
        List<Resume> actualResumes = storage.getAllSorted();
        Collections.sort(actualResumes);
        assertEquals(expectedResumes, actualResumes);

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