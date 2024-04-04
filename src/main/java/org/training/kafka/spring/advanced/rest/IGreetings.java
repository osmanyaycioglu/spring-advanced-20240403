package org.training.kafka.spring.advanced.rest;

import org.training.kafka.spring.advanced.aop.MethodTime;

public interface IGreetings {

    @MethodTime(tag = "greet")
    String greet(String name,String surname);
    
}
