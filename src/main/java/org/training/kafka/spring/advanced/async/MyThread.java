package org.training.kafka.spring.advanced.async;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class MyThread extends Thread {

    private ApplicationContext applicationContext;

    public MyThread(final ApplicationContext applicationContextParam) {
        applicationContext = applicationContextParam;
    }

    @Override
    public void run() {
        MyAsyncBean beanLoc = applicationContext.getBean(MyAsyncBean.class);
        super.run();
    }

}
