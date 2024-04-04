package a.b.c;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:my.properties")
public class MyOtherConfig {

    @Value("${my.prop.lang}")
    private String test;

    @Bean
    public MyAnotherPackageBean myAnotherPackageBean(){
        return new MyAnotherPackageBean();
    }

}
