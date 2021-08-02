package ru.javawebinar.basejava;

public class Deadlock {
    public static void main(String[] args) {
        Deadlock lock = new Deadlock();
        Object x = new Object();
        Object y = new Object();
        lock.createDeadlock(x, y).start();
        lock.createDeadlock(y, x).start();

    }

    public Thread createDeadlock(Object x, Object y) {
        return new Thread(() -> {
            synchronized (y) {
                System.out.println(Thread.currentThread().getName() + " Мы залочились по объекту");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " Пробуем залочиться по другому объекту");
                synchronized (x) {
                    System.out.println(Thread.currentThread().getName() + " Мы залочились по обькту");
                }
            }
        });
    }
}
