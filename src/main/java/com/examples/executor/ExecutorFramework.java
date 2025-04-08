package com.examples.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorFramework {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        final int n = 9;
        ExecutorService executorService = Executors.newFixedThreadPool(n);
        for(int i=1; i<n; i++) {
            int finalI = i;
            executorService.submit(() -> {
                int result = factorial(finalI);
                System.out.printf(String.format("\nFactorial of %d: %d", finalI, result));
            });
        }
        // Shuts down the executor service, otherwise it can't be reused
        executorService.shutdown();

        try {
            // Wait for defined time
            // executorService.awaitTermination(10, TimeUnit.SECONDS);

            // Wait for indefinite time along with checking at every 10 milliseconds
            while(!executorService.awaitTermination(10, TimeUnit.MILLISECONDS)) {
                System.out.println("Waiting...");
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // If we don't await termination of executorService, main thread will execute this at first.
        System.out.println("\nTime taken:" + (System.currentTimeMillis() - startTime));

    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i < n; i++) {
            result *=i;
        }
        return result;
    }
}
