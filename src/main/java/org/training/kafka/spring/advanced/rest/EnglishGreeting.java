package org.training.kafka.spring.advanced.rest;

import org.springframework.stereotype.Component;

public class EnglishGreeting implements IGreetings{

    @Override
    public String greet(final String name,
                        final String surname) {
        return "Hello " + name + " " + surname;
    }
}
