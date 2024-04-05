package org.training.kafka.spring.advanced.security.backup;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
//import org.springframework.security.web.util.matcher.OrRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import com.adenon.network.security.user.UserDetailService;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@Order(2)
//public class RestSecurityConfigure extends WebSecurityConfigurerAdapter {
//
//    private static final RequestMatcher PUBLIC_URLS    = new OrRequestMatcher(new AntPathRequestMatcher("/public/**"));
//    private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(RestSecurityConfigure.PUBLIC_URLS);
//
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Autowired
//    private UserDetailService           userDetailsService;
//
//    @Autowired
//    private JWTRequestFilter            jwtRequestFilter;
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(this.userDetailsService)
//            .passwordEncoder(this.passwordEncoder());
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        http.antMatcher("/**")
//            .authorizeRequests()
//            .antMatchers("/api/v1/auth/**")
//            .permitAll()// all other requests need to be authenticated
//            .antMatchers("/actuator/**")
//            .hasAnyRole("ADMIN",
//                        "SUPER_ADMIN")
//            .antMatchers("/user/management/**")
//            .hasAnyRole("SUPER_ADMIN")
//            .antMatchers("/api/v1/**")
//            .hasAnyRole("SUPER_ADMIN",
//                        "ADMIN",
//                        "USER")
//            .antMatchers("/int/v1/discovery/**")
//            .hasAnyRole("SUPER_ADMIN",
//                        "ADMIN",
//                        "USER",
//                        "INTEGRATOR")
//            .anyRequest()
//            .authenticated()
//            .and()
//            .exceptionHandling()
//            .authenticationEntryPoint(this.jwtAuthenticationEntryPoint)
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and()
//            .csrf()
//            .disable()
//            .formLogin()
//            .disable()
//            .httpBasic()
//            .disable()
//            .logout()
//            .disable();
//
//        http.addFilterBefore(this.jwtRequestFilter,
//                             UsernamePasswordAuthenticationFilter.class);
//    }
//
//}
