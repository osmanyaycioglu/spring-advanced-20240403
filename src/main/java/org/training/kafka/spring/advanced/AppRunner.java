package org.training.kafka.spring.advanced;

import a.b.c.MyAnotherPackageBean;
import a.b.c.MyAnotherPackageBeanProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.training.kafka.spring.advanced.async.MyAsyncBean;

import java.util.concurrent.Future;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private MyAnotherPackageBean myAnotherPackageBean;

    @Autowired
    private MyAsyncBean myAsyncBean;

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        for (int i = 0; i < 100; i++) {
            System.out.println(myAnotherPackageBean.hello("ali"));
        }


        MyAnotherPackageBean myAnotherPackageBeanLoc = new MyAnotherPackageBean();
        for (int i = 0; i < 20_000; i++) {
            myAnotherPackageBeanLoc.hello("ali");
        }
        System.gc();
        Thread.sleep(3_000);

        long delta = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            myAnotherPackageBeanLoc.hello("ali");
        }

        System.out.println("Real delta :"+(System.currentTimeMillis() - delta));
        Future<String> stringFutureLoc = myAsyncBean.myMethod("osman");
        System.out.println("AppRunner thread : " + Thread.currentThread().getName());
        System.out.println(stringFutureLoc.get());
    }
}
