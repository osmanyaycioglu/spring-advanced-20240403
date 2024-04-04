package org.training.kafka.spring.advanced.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.kafka.spring.advanced.aop.MethodTime;
import org.training.kafka.spring.advanced.aop.MyAspect;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/hello")
// @RequestScope
public class HelloWorldController {
    private final IGreetings          greetings;
    private       List<String>        names           = new Vector<>();
    private       AtomicLong          counter         = new AtomicLong();
    private       Map<String, String> stringStringMap = new ConcurrentHashMap<>(1_000_000,0.9f,1_000);
    private long counter2 = 0;

    @Qualifier("otherGreetings")
    @Autowired(required = false)
    private IGreetings oGreetings;

    @Autowired
    private MyAspect myAspect;

    public HelloWorldController(@Qualifier("greetings") IGreetings greetings) {
        this.greetings = greetings;
    }

    public synchronized void increaseCounter2(){
        counter2++;
    }

    public synchronized long getCounter2(){
        return counter2;
    }

    @GetMapping("/hello1")
    @MethodTime(tag = "hello1")
    public String hello(@RequestParam String name,
                        @RequestParam String surname) {
        List<String> test = new ArrayList<>();
        names.add(name);
        counter.incrementAndGet();
        String greetLoc = greetings.greet(name,
                                          surname);
        System.out.println("ret : " +  greetLoc);
        return greetLoc;
    }

    @GetMapping("/hello2")
    @MethodTime(tag = "hello2")
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


    @GetMapping("/deltas")
    public Map<String, AtomicLong> getDeltas() {
        return myAspect.getDeltaTimes();
    }

    //    @PostConstruct
//    public void init(){
//        System.out.println("xyz");
//    }

}
