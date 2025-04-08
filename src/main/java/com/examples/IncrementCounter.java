package com.examples;

import com.examples.counter.Counter;

public class IncrementCounter extends Thread {
    Counter counter;

    public IncrementCounter(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            this.counter.increment();
        }
    }
}
