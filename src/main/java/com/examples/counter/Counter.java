package com.examples.counter;

public abstract class Counter {
    public int counter = 0;

    public abstract void increment();

    public int getCounter() {
        return counter;
    }
}
