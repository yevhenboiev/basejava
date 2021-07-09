package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws RuntimeException {
//        File file = new File(".//.gitignore");
//        try {
//            System.out.println(file.getCanonicalPath());
//        } catch (IOException e) {
//            throw new RuntimeException("Error", e);
//        }
        File dir = new File("/home/java-developer/Документы/basejava/src/ru/javawebinar/basejava");
        if (dir.isDirectory()) {
            for (File files : Objects.requireNonNull(dir.listFiles())) {
                if (files.isDirectory()) {
                    System.out.println("It's directory: " + files.getName());
                } else if (files.isFile()) {
                    System.out.println("It's file: " + files.getName());
                }
            }
        }
    }
}