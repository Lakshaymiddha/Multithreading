package com.examples.locks;

import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.locks.Lock;

public class LockFairnessExample {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Creating a ReentrantLock with fairness set to true.
     * This ensures that threads acquire the lock in the order they requested it (first-come, first-served).
     * If fairness is false (default), the order is not guaranteed — it may prefer throughput and allow barging.
     * It avoids resource starvation for an individual thread.
     */
    private final Lock fairnessLock = new ReentrantLock(true);

    // To test unfair locking, you can use:
    // private final Lock fairnessLock = new ReentrantLock();

    public void accessResource() {
        fairnessLock.lock();
        try {
            logger.log(Level.INFO, "Thread:{0} accessing the resource.", Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            logger.log(Level.INFO, "Thread:{0} unlocking the lock after accessing the resource.", Thread.currentThread().getName());
            fairnessLock.unlock();
        }
    }

    public static void main(String[] args) {
        final LockFairnessExample lockFairnessExample = new LockFairnessExample();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                lockFairnessExample.accessResource();
            }
        };
        Thread t1 = new Thread(task, "Thread 1");
        Thread t2 = new Thread(task, "Thread 2");
        Thread t3 = new Thread(task, "Thread 3");

        try {
            t1.start();
            // Sleep in order to wait the another thread to request for resources.
            Thread.sleep(50);
            t2.start();
            Thread.sleep(50);
            t3.start();
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
