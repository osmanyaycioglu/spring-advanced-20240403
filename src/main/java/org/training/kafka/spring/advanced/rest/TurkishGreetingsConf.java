package org.training.kafka.spring.advanced.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("tr")
@Configuration
public class TurkishGreetingsConf {

    @Bean
    public IGreetings greetings(){
        return new TurkishGreeting();
    }

}
