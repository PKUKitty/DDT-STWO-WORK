/*
 * Copyright (c) 2017.
 * Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ddt.dp.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("Program started");

        List<Task> tasks = new ArrayList<>();

        tasks.add(new PotatoPeelingTask(3));
        tasks.add(new PotatoPeelingTask(6));
        tasks.add(new CoffeeMakingTask(2));
        tasks.add(new CoffeeMakingTask(6));
        tasks.add(new PotatoPeelingTask(4));
        tasks.add(new CoffeeMakingTask(2));
        tasks.add(new PotatoPeelingTask(4));
        tasks.add(new CoffeeMakingTask(9));
        tasks.add(new PotatoPeelingTask(3));
        tasks.add(new CoffeeMakingTask(2));
        tasks.add(new PotatoPeelingTask(4));
        tasks.add(new CoffeeMakingTask(2));
        tasks.add(new CoffeeMakingTask(7));
        tasks.add(new PotatoPeelingTask(4));
        tasks.add(new PotatoPeelingTask(5));

        // Creates a thread pool that reuses a fixed number of threads operating off a shared
        // unbounded queue. At any point, at most nThreads threads will be active processing
        // tasks. If additional tasks are submitted when all threads are active, they will wait
        // in the queue until a thread is available.
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Allocate new worker for each task
        // The worker is executed when a thread becomes
        // available in the thread pool
        for (Task task : tasks) {
            executor.execute(new Worker(task));
        }

        // All tasks were executed, now shutdown
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
        LOGGER.info("Program finished");
    }
}
