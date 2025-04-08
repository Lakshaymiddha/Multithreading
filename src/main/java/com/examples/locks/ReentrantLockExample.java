package com.examples.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReentrantLockExample {
    Lock lock = new ReentrantLock();
    Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * If we forget to match unlock() calls, the lock will never be released, causing deadlock for other threads.
     * This pattern is safe only because we have balanced every lock() with an unlock().
     */
    public void outerMethod() {
        lock.lock();
        try {
            logger.log(Level.INFO, "Outer Method.");
            innerMethod();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Exception in outerMethod:{0}", e);
        } finally {
            lock.unlock();
        }
    }

    public void innerMethod() {
        /*
         * A thread that already holds the lock can acquire it again — it just increments an internal hold count.
         * And each time you call unlock(), it decrements the hold count. The lock is fully released only when the hold
         * count reaches zero.
         */
        lock.lock();
        try {
            logger.log(Level.INFO, "Inner Method.");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample reentrantLockExample = new ReentrantLockExample();
        reentrantLockExample.outerMethod();
    }
}
