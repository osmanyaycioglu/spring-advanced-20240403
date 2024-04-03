package org.training.kafka.spring.advanced.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.training.kafka.spring.advanced.conditonal.CheckConfig;

@CheckConfig(ckey = "my.prop.lang",cvalue = "esp")
@Configuration
public class SpanishGreetingsConf {

    @Bean
    public IGreetings otherGreetings(){
        System.out.println("SPANISH yaratıldı");
        return new SpanishGreeting();
    }

}
