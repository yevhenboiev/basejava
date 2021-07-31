package ru.javawebinar.basejava;

public class Deadlock {
    public static void main(String[] args) {
        Object x = new Object();
        Object y = new Object();

        new Thread(() -> {
            synchronized (x) {
                System.out.println(Thread.currentThread().getName() + " Мы залочились по объекту Х");

                System.out.println(Thread.currentThread().getName() + " Пробуем залочиться по объекту Y");
                synchronized (y) {
                    System.out.println(Thread.currentThread().getName() + " Мы залочились по обькту Y");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (y) {
                System.out.println(Thread.currentThread().getName() + " Мы залочились по объекту Y");

                System.out.println(Thread.currentThread().getName() + " Пробуем залочиться по объекту X");
                synchronized (x) {
                    System.out.println(Thread.currentThread().getName() + " Мы залочились по обькту X");
                }
            }
        }).start();

    }
}
