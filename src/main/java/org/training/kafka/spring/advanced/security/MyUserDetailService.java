package org.training.kafka.spring.advanced.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class MyUserDetailService implements UserDetailsService {

    private Map<String, UserDetails> userDetailsMap = new HashMap<>();
    private BCryptPasswordEncoder    cryptPasswordEncoder;

    public MyUserDetailService(BCryptPasswordEncoder cryptPasswordEncoderParam) {
        cryptPasswordEncoder = cryptPasswordEncoderParam;
        userDetailsMap.put("osman",
                           User.builder()
                               .username("osman")
                               .password(cryptPasswordEncoder.encode("123456"))
                               .roles("ADMIN")
                               .build());
        userDetailsMap.put("ali",
                           User.builder()
                               .username("ali")
                               .password(cryptPasswordEncoder.encode("123456"))
                               .roles("USER")
                               .build());
        userDetailsMap.put("veli",
                           User.builder()
                               .username("veli")
                               .password(cryptPasswordEncoder.encode("123456"))
                               .roles("VIEWER")
                               .build());
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        UserDetails userDetailsLoc = userDetailsMap.get(username);
        if (userDetailsLoc == null) {
            throw new UsernameNotFoundException("no user");
        }

        return User.builder()
                   .username(userDetailsLoc.getUsername())
                   .password(userDetailsLoc.getPassword())
                   .authorities(userDetailsLoc.getAuthorities())
                   .build();
    }
}
