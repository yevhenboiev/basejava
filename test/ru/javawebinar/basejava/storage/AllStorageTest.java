package ru.javawebinar.basejava.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapStorageTest.class,
        MapResumeStorageTest.class,
        PathStorageTest.class,
        FileStorageTest.class,
        XmlPathStorageTest.class,
        DataPathStorageTest.class,
        SqlStorageTest.class
})

public class AllStorageTest {
}
