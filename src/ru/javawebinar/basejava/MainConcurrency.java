package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int count;
    private static final int COUNT = 10000;

    public static void main(String[] args) {
        List<Thread> threadList = new ArrayList<>(COUNT);

        for (int i = 0; i < COUNT; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
            });
            threadList.add(thread);
            thread.start();
            threadList.forEach((t) -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(count);
    }

    private static synchronized void inc() {
        count++;
    }
}
