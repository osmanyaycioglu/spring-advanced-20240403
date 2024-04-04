package org.training.kafka.spring.advanced.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Component
public class MyAsyncBean {

    @Async("myThreadPool")
    public Future<String> myMethod(String name) {
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException eParam) {
        }
        return CompletableFuture.completedFuture("Async hello : " + name + " Thread : " + Thread.currentThread()
                                                                                                .getName());
    }

}
