package a.b.c;

import org.springframework.stereotype.Component;


public class MyAnotherPackageBean {

    public String hello(String name){
        return "Hello from other package " + name;
    }

}
