package org.training.kafka.spring.advanced.rest;

import org.springframework.stereotype.Component;

public class TurkishGreeting implements IGreetings{

    @Override
    public String greet(final String name,
                        final String surname) {
        return "Selam " + name + " " + surname;
    }
}
