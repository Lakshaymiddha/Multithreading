package com.examples.locks.readwrite;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static int count = 0;
    static ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = reentrantReadWriteLock.readLock();
    private static final Lock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        Runnable readTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(String.format("ReadWriteLockExample.ReadTask.run- Thread:%s, reading value:%d", Thread.currentThread().getName(), getCount()));
                }
            }
        };

        Runnable writeTask = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    increment();
                    System.out.println(String.format("ReadWriteLockExample.WriteTask.run- Thread:%s, writing value:%d", Thread.currentThread().getName(), getCount()));
                }
            }
        };

        Thread readThread1 = new Thread(readTask, "Read Thread 1");
        Thread readThread2 = new Thread(readTask, "Read Thread 2");
        Thread writeThread = new Thread(writeTask, "Write Thread 1");
        try {
            readThread1.start();
            // We can try putting Thread.sleep at different places to see the behaviour.
            Thread.sleep(50);
            writeThread.start();
            Thread.sleep(50);
            readThread2.start();
            writeThread.join();
            readThread1.join();
            readThread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void increment() {
        writeLock.lock();
        try {
            count++;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

    private static int getCount() {
        readLock.lock();
        try {
            return count;
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
        }
        return -1;
    }
}
