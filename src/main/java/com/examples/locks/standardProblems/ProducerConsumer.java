package com.examples.locks.standardProblems;

public class ProducerConsumer {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();
        Thread producer = new Thread(new Producer(sharedResource));
        Thread consumer = new Thread(new Consumer(sharedResource));

        producer.start();
        consumer.start();
    }
}

class Producer implements Runnable {
    private SharedResource sharedResource;

    public Producer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            // sharedResource.produce(i);
            // Produce using BlockingQueue
            sharedResource.produceInQueue(i);
        }
    }
}

class Consumer implements Runnable {
    private SharedResource sharedResource;

    public Consumer(SharedResource sharedResource) {
        this.sharedResource = sharedResource;
    }
    @Override
    public void run() {
        for(int i=0;i<10; i++) {
            // sharedResource.consume();
            // Consume using BlockingQueue
            sharedResource.consumeFromQueue();
        }
    }
}