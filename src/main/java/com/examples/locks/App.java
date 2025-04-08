package com.examples.locks;

public class App {

    public static void main(String[] args) throws InterruptedException {
        final BankAccount synchronizedBankAccount = new BankAccountSynchronized(100);
        Runnable task = new Runnable() {
            @Override
            public void run() {
                synchronizedBankAccount.withdraw(50);
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // Example for BankAccountWithLock.
        final BankAccount BankAccountWithLock = new BankAccountWithLock(100);
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                BankAccountWithLock.withdraw(50);
            }
        };
        t1 = new Thread(task2, "Thread 1");
        t2 = new Thread(task2, "Thread 2");
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
