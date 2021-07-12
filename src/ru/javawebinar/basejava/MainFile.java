package ru.javawebinar.basejava;

import java.io.File;
import java.util.Objects;

public class MainFile {
    public static void main(String[] args) throws RuntimeException {
        File dir = new File("./src/ru/javawebinar/basejava");
        MainFile mainFile = new MainFile();
        mainFile.recourseMethod(dir, "");
    }

    public void recourseMethod(File directory, String str) {
        for (File files : Objects.requireNonNull(directory.listFiles())) {
            if (files.isFile()) {
                System.out.println(str + "File: " + files.getName());
            } else if (files.isDirectory()) {
                System.out.println("Directory: " + files.getName());
                recourseMethod(files, "    ");
            }
        }
    }
}