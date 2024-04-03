package org.training.kafka.spring.advanced.rest;

public class SpanishGreeting implements IGreetings{

    @Override
    public String greet(final String name,
                        final String surname) {
        System.out.println("esp sonuç : Ola " + name + " " + surname);
        return "Ola " + name + " " + surname;
    }
}
