package ru.javawebinar.basejava.storage;

import static org.junit.Assert.*;

public class ObjectStreamPathStorageTest extends AbstractStorageTest{

    public ObjectStreamPathStorageTest() {
        super(new ObjectStreamPathStorage(STORAGE_PATH));
    }
}