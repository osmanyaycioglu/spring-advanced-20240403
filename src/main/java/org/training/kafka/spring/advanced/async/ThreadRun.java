package org.training.kafka.spring.advanced.async;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadRun {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLockLoc = new ReentrantReadWriteLock();

        for (int i = 0; i < 10; i++) {
            MyThread threadLoc = new MyThread(null);
            threadLoc.start();
        }
    }
}
