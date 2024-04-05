package org.training.kafka.spring.advanced.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

public class JWTService {

    private static final Logger logger = LoggerFactory.getLogger(JWTService.class);

    private Key key;

    public JWTService() {
        key = Keys.hmacShaKeyFor("JWTDenemeYanilmaTrialTestlasjkdklaksdjbasdkjasdkjashdklajsd".getBytes());
    }

    public String createToken(String username,
                              String group) {
        return Jwts.builder()
                   .subject(username)
                   .issuedAt(new Date())
                   .expiration(Date.from(ZonedDateTime.now()
                                                      .plusHours(1)
                                                      .toInstant()))
                   .claim("group",
                          group)
                   .signWith(key)
                   .compact();
    }

    public Jws<Claims> validate(String token) {
        JwtParser jwtParserLoc = Jwts.parser()
                                     .setSigningKey(key)
                                     .build();
        try {
            Jws<Claims> claimsJwsLoc = jwtParserLoc.parseSignedClaims(token);
            return claimsJwsLoc;
        } catch (Exception exp) {
            logger.error("[JWTService][validate]-> *Error* : " + exp.getMessage(),
                         exp);
        }

        return null;
    }

}
