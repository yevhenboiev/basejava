package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) throws RuntimeException {

        File file = new File(".//.gitignore");
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
        File dir = new File("/home/java-developer/Документы/basejava/src/ru/javawebinar/basejava");
        for (String name : dir.list()) {
            System.out.println(name);
        }

        try (FileInputStream fil = new FileInputStream(file)) {
            System.out.println(fil.read());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
    }
}