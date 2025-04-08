package com.examples.locks.standardProblems;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class SharedResource {
    private int data;
    private boolean doesBufferContainsData;
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    public synchronized void produce(int value) {
        while (doesBufferContainsData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Produced data: " + value);
        doesBufferContainsData = true;
        data = value;
        notify();
    }

    public synchronized void produceInQueue(int value) {
        try {
            queue.put(value); // blocks if queue is full
            System.out.println("Produced data: " + value);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public synchronized int consume() {
        while (!doesBufferContainsData) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Consumed data: " + data);
        doesBufferContainsData = false;
        // We are taking a lock on SharedResource object using synchronized method. This method will notify all the
        // threads which are waiting to take lock on SharedResource object.
//        notify();
        return data;
    }

    public synchronized int consumeFromQueue() {
        int data = -1;
        try {
            data = queue.take(); // blocks if queue is empty
            System.out.println("Consumed data: " + data);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return data;
    }
}
