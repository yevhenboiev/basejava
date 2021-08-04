package ru.javawebinar.basejava;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency {
    private static int count;
    private static final int COUNT = 10000;
    private static final Lock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
//        CompletionService completionService = new ExecutorCompletionService(executorService);

        for (int i = 0; i < COUNT; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    inc();
                }
                latch.countDown();
            });
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    inc();
//                }
//                latch.countDown();
//            });
//            thread.start();
        }
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
        System.out.println(count);
    }

    private static void inc() {
        LOCK.lock();
        try {
            count++;
        } finally {
            LOCK.unlock();
        }
    }
}
