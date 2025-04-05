package com.examples;

/**
 * Hello world!
 */
public class App extends Thread {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new IncrementCounter(counter);
        Thread t2 = new IncrementCounter(counter);
        System.out.println();
    }
}
