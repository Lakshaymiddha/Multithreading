package com.examples.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        // If we want to return something from lambda, then Callable FI is used. Otherwise Runnable FI is used.
        // Future is returned by submit
        // Callable
        Future<String> future = executorService.submit(() -> "Hello");

        // Runnable
        Future<?> future2 = executorService.submit(() -> System.out.println("Hello"));

        System.out.println(future.get());
        System.out.println("Future2.get(): " + future2.get());
        if (future.isDone()) {
            System.out.println("Future: " + future.get());
        }
        if (future2.isDone()) {
            System.out.println("Future2: " + future2.get());
        }
    }
}
