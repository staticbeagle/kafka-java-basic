package com.wildbitsfoundry;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {

        Runnable producerTask = new MessageProducer();
        Runnable consumerTask = new MessageConsumer();
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(producerTask, 0, 1, TimeUnit.SECONDS);
        executorService.execute(consumerTask);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shut down requested");
            shutdownExecutor(scheduledExecutorService, 2);
            shutdownExecutor(executorService, 2);
        }));
    }

    public static void shutdownExecutor(ExecutorService executorService, long timeout) {
        try {
            executorService.shutdown();
            if(!executorService.awaitTermination(timeout, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
                if(!executorService.awaitTermination(timeout, TimeUnit.SECONDS)) {
                    System.out.println("Did not terminate on time");
                }
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}