package ru.javawebinar.basejava;

public class DeadlockMoney {
    public static void main(String[] args) {
        Account1 account1 = new Account1();
        Account2 account2 = new Account2();
        account1.acc2 = account2;
        account2.acc1 = account1;

        new Thread(() -> {
            synchronized (account1) {
                System.out.println("I have cash : " + account1.cash);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Try watch number second Account");
                synchronized (account2) {
                    System.out.println("Number second Account : " + account1.getNumberOtherAccount());
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (account2) {
                System.out.println("I have cash : " + account2.cash);

                System.out.println("Try watch number first Account");
                synchronized (account1) {
                    System.out.println("Number first Account :  " + account2.getNumberOtherAccount());
                }
            }
        }).start();
    }
}

class Account1 {
    Account2 acc2;
    public int cash = 100;
    private final int number = (int) (Math.random() * 10);

    public int getNumber() {
        return number;
    }

    public int getNumberOtherAccount() {
        return acc2.getNumber();
    }
}

class Account2 {
    Account1 acc1;
    public int cash = 150;
    private final int number = (int) (Math.random() * 10);

    public int getNumber() {
        return number;
    }

    public int getNumberOtherAccount() {
        return acc1.getNumber();
    }
}
