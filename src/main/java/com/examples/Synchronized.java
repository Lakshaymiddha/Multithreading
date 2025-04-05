package com.examples;

public class Synchronized {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        Thread t1 = new IncrementCounter(counter);
        Thread t2 = new IncrementCounter(counter);
        t1.run();
        t2.run();
        // Calling .run() does not start a new thread, it just calls the method in the main thread, like a regular function.
        System.out.println("Counter after invoking run() method: " + counter.getCounter());

        Counter counter2 = new Counter();
        t1 = new IncrementCounter(counter2);
        t2 = new IncrementCounter(counter2);
        // Start new threads for t1 and t2.
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        // Calling with synchronized block in Counter.increment() method would solve race condition.
        System.out.println("Counter after invoking run() method: " + counter2.getCounter());
    }
}
