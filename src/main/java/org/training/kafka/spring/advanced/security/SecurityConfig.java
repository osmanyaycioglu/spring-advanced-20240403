package org.training.kafka.spring.advanced.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configureSec(HttpSecurity httpSecurityParam) throws Exception {
        return httpSecurityParam.cors(CorsConfigurer::disable)
                                .csrf(CsrfConfigurer::disable)
                                .authorizeHttpRequests(
                                        a -> a.requestMatchers("/actuator/**",
                                                               "/sec/**")
                                              .anonymous()
                                              .requestMatchers("/api/v1/hello/**")
                                              .hasAnyRole("ADMIN","USER")
                                              .requestMatchers("/api/v1/employee/**")
                                              .hasAnyRole("USER")
                                              .anyRequest()
                                              .authenticated()
                                )
                                .addFilterBefore(jwtFilter(),
                                                 UsernamePasswordAuthenticationFilter.class)
                                .httpBasic(HttpBasicConfigurer::disable)
                                .formLogin(FormLoginConfigurer::disable)
                                .sessionManagement(SessionManagementConfigurer::disable)
                                .build();
    }

    @Bean
    public JWTService jwtService() {
        return new JWTService();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MyUserDetailService myUserDetailService() {
        return new MyUserDetailService(encoder());
    }

    @Bean
    public AuthenticationProvider authenticationManager() throws Exception {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailService());
        authenticationProvider.setPasswordEncoder(encoder());
        return authenticationProvider;
    }

    @Bean
    public JWTFilter jwtFilter() {
        return new JWTFilter();
    }

}
