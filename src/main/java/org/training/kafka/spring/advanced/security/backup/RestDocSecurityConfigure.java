package org.training.kafka.spring.advanced.security.backup;

//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
//@Order(1)
//public class RestDocSecurityConfigure extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//
//        http.antMatcher("/apidoc/**")
//            .authorizeRequests()
//            .antMatchers("/apidoc/**")
//            .hasAnyRole("ADMIN",
//                        "SUPER_ADMIN")
//            .and()
//            .httpBasic()
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .csrf()
//            .disable()
//            .cors()
//            .disable()
//            .formLogin()
//            .disable()
//            .logout()
//            .disable();
//
//    }
//
//}
