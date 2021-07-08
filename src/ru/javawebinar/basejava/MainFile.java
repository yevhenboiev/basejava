package ru.javawebinar.basejava;

import java.io.File;

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
            for (File files : dir.listFiles()) {
                if (files.isDirectory()) {
                    System.out.println(files.getName());
                    for (File filesDirectory : files.listFiles()) {
                        System.out.println("\t" + filesDirectory.getName());
                    }
                } else {
                    System.out.println(files.getName());
                }
            }
        } else {
            System.out.println(dir.getName());
        }
    }
}