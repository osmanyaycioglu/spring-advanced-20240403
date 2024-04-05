package org.training.kafka.spring.advanced.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.kafka.spring.advanced.security.JWTService;
import org.training.kafka.spring.advanced.security.MyUserDetailService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/sec")
@RequiredArgsConstructor
public class LoginController {

    private final MyUserDetailService   myUserDetailService;
    private final BCryptPasswordEncoder encoder;
    private final JWTService            jwtService;
    private final AuthenticationProvider authenticationProvider;


    @PostMapping("/login")
    public String login(@RequestBody UserInfo userInfoParam) {
        Authentication authenticateLoc = new UsernamePasswordAuthenticationToken(userInfoParam.getUsername(),
                                                                                 userInfoParam.getPassword());

        Authentication authenticateRealLoc = authenticationProvider.authenticate(authenticateLoc);
//        UserDetails userDetailsLoc = myUserDetailService.loadUserByUsername(userInfoParam.getUsername());
//        if (userDetailsLoc == null) {
//            return "Not valid";
//        }
//        String      encodeLoc      = encoder.encode(userInfoParam.getPassword());
            return jwtService.createToken(userInfoParam.getUsername(),
                                          authenticateRealLoc.getAuthorities()
                                                        .stream()
                                                        .map(GrantedAuthority::getAuthority)
                                                        .collect(Collectors.joining(",")));
    }

}
