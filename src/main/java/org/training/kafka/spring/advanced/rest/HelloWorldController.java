package org.training.kafka.spring.advanced.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloWorldController {
    private final IGreetings greetings;

    @Qualifier("otherGreetings")
    @Autowired(required = false)
    private IGreetings oGreetings;

    public HelloWorldController(@Qualifier("greetings") IGreetings greetings) {
        this.greetings = greetings;
    }

    @GetMapping("/hello1")
    public String hello(@RequestParam String name,
                        @RequestParam String surname) {
        return greetings.greet(name,
                               surname);
    }

    @GetMapping("/hello2")
    public String hello2(@RequestParam String name,
                         @RequestParam String surname) {
        if (oGreetings != null) {
            System.out.println("OGreetings : " + oGreetings.getClass()
                                                           .getName());
            return oGreetings.greet(name,
                                    surname);
        }
        return greetings.greet(name,
                               surname);
    }

    //    @PostConstruct
//    public void init(){
//        System.out.println("xyz");
//    }

}
