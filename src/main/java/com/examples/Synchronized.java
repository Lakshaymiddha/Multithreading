package com.examples;

import com.examples.counter.Counter;
import com.examples.counter.SynchronizedCounter;
import com.examples.counter.UnsynchronizedCounter;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Synchronized {
    private static final Logger logger = Logger.getLogger(Synchronized.class.getName());
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new UnsynchronizedCounter();
        Thread t1 = new IncrementCounter(counter);
        Thread t2 = new IncrementCounter(counter);
        t1.run();
        t2.run();
        // Calling .run() does not start a new thread, it just calls the method in the main thread, like a regular function.
        // Even with UnsynchronizedCounter, it will result in expected result without race condition.
        logger.log(Level.INFO, "Single-threaded (Unsynchronized): Counter = {0} (Expected: 2,000,000)", counter.getCounter());

        Counter unsynchronizedCounter = new UnsynchronizedCounter();
        t1 = new IncrementCounter(unsynchronizedCounter);
        t2 = new IncrementCounter(unsynchronizedCounter);
        // Start new threads for t1 and t2.
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // Invokes increment without synchronized block.
        logger.log(Level.INFO, "Multi-threaded (Unsynchronized): Counter = {0} (Expected: 2,000,000)", unsynchronizedCounter.getCounter());

        Counter synchronizedCounter = new SynchronizedCounter();
        t1 = new IncrementCounter(synchronizedCounter);
        t2 = new IncrementCounter(synchronizedCounter);
        // Start new threads for t1 and t2.
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // Invokes increment with synchronized block.
        logger.log(Level.INFO, "Multi-threaded (Synchronized): Counter = {0} (Expected: 2,000,000)", synchronizedCounter.getCounter());
    }
}
