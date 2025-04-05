package com.examples;

public class Counter {
    public int counter = 0;

    public void increment() {
        // Without synchronized block increment would cause race condition.
        synchronized (this) {
            counter++;
        }
    }

    public int getCounter() {
        return counter;
    }
}
