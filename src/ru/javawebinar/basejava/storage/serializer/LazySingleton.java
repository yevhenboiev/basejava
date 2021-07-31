package ru.javawebinar.basejava.storage.serializer;


public class LazySingleton {
    volatile private static LazySingleton INSTANCE;

    private LazySingleton() {
    }

//    private static class LazySingletonHolder {
//        private static final LazySingleton INSTANCE = new LazySingleton();
//    }

    public static LazySingleton getInstance() {
//        return LazySingletonHolder.INSTANCE;
        if (INSTANCE == null) {
            INSTANCE = new LazySingleton();
            synchronized (LazySingleton.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LazySingleton();
                }
            }
        }
        return INSTANCE;
    }
}
