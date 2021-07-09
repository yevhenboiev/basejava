package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws RuntimeException {
        File dir = new File("./src/ru/javawebinar/basejava");
        MainFile mainFile = new MainFile();
        mainFile.recourseMethod(dir);
    }

    public void recourseMethod(File directory) {
        for (File files : Objects.requireNonNull(directory.listFiles())) {
            if (files.isDirectory()) {
                System.out.println("It's directory: " + files.getName());
                recourseMethod(files);
            } else if (files.isFile()) {
                System.out.println("It's file: " + files.getName());
            }
        }
    }
}