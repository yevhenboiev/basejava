package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, InvocationTargetException {
        Resume r = new Resume();
        Class<?> c = Class.forName("ru.javawebinar.basejava.model.Resume");
        Method  method = c.getMethod ("toString");
        System.out.println(method.invoke(r));
    }
}