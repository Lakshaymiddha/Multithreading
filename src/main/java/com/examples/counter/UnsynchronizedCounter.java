package com.examples.counter;

public class UnsynchronizedCounter extends Counter {
    @Override
    public void increment() {
        counter++;
    }
}
