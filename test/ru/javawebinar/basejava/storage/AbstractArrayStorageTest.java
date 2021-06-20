package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                Resume r_i = new Resume();
                storage.save(r_i);
            }
        } catch (StorageException exp) {
            fail("Переполнение произошло раньше времени");
        }
        Resume r_10000 = new Resume();
        storage.save(r_10000);
    }
 }