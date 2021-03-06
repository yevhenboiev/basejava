package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.model.ResumeTestData;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    private final String UUID_1 = UUID.randomUUID().toString();
    private final String UUID_2 = UUID.randomUUID().toString();
    private final String UUID_3 = UUID.randomUUID().toString();
    private final String UUID_4 = UUID.randomUUID().toString();

    private final Resume r1 = ResumeTestData.fillResume(UUID_1, "Name 1");
    private final Resume r2 = ResumeTestData.fillResume(UUID_2, "Name 2");
    private final Resume r3 = ResumeTestData.fillResume(UUID_3, "Name 3");

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
        storage.update(new Resume("uuid5", "Name 5"));
    }

    @Test
    public void save() {
        Resume r4 = ResumeTestData.fillResume(UUID_4, "Name 4");
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