package org.training.kafka.spring.advanced.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("eng")
@Configuration
public class EnglishGreetingsConf {

    @Bean
    public IGreetings greetings(){
        return new EnglishGreeting();
    }

}
