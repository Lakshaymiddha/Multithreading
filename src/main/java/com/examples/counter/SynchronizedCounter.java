package com.examples.counter;

public class SynchronizedCounter extends Counter {
    @Override
    public void increment() {
        synchronized (this) {
            counter++;
        }
    }
}
