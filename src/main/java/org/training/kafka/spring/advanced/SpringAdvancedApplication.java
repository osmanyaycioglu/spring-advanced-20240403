package org.training.kafka.spring.advanced;

import a.b.c.MyOtherConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@SpringBootApplication
//@SpringBootApplication(scanBasePackages = {"org.training.kafka.spring.advanced",
//                                           "a.b.c"
//})
@Import(MyOtherConfig.class)
@EnableAsync
@EnableAspectJAutoProxy
public class SpringAdvancedApplication {

    @Bean
    public Executor myThreadPool(){
        return Executors.newFixedThreadPool(10);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringAdvancedApplication.class,
                              args);
    }

}
