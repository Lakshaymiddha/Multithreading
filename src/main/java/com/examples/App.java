package com.examples;

import com.examples.counter.Counter;
import com.examples.counter.UnsynchronizedCounter;

/**
 * Hello world!
 */
public class App extends Thread {
    public static void main(String[] args) {
        Counter counter = new UnsynchronizedCounter();
        Thread t1 = new IncrementCounter(counter);
        Thread t2 = new IncrementCounter(counter);
        System.out.println();
    }
}
