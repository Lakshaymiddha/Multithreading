package com.examples.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;

public class BankAccountWithLock extends BankAccount {
    Lock lock = new ReentrantLock();

    public BankAccountWithLock(int balance) {
        super(balance);
    }

    @Override
    public void withdraw(int amount) {
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                if (amount <= balance) {
                    logger.log(Level.INFO, "Processing withdraw of amount: {0}", amount);
                    try {
                        Thread.sleep(3000);
                        balance -= amount;
                    } catch (InterruptedException e) {
                        logger.log(Level.INFO, "Withdraw process interrupted during withdraw of amount: {0}", amount);
                    } finally {
                        // Unlock after work has been completed.
                        lock.unlock();
                    }
                    logger.log(Level.INFO, "Remaining balance: {0}", balance);
                } else {
                    logger.log(Level.INFO, "Insufficient balance: {0}", balance);
                }
            } else {
                logger.log(Level.INFO, "Thread:{0} could not acquire lock and withdraw amount:{1} Balance:{2}", new Object[]{Thread.currentThread().getName(), amount, balance});
            }
        } catch (InterruptedException e) {
            /*
             * Catching the exception clears the thread’s interrupted status.
             * Setting the interrupted flag back to true is important here as it allows higher-level code (or thread
             * policies) know that this thread was interrupted and should possibly exit or handle it gracefully
             */
            Thread.currentThread().interrupt();
            logger.warning("Thread:{0} was interrupted while trying to acquire the lock" + Thread.currentThread().getName());
        }
        /*
         * Handles gracefully if the thread was interrupted
         */
        if (Thread.currentThread().isInterrupted()) {
            logger.log(Level.INFO, "Thread:{0} was interrupted.", Thread.currentThread().getName());
        }

    }
}
