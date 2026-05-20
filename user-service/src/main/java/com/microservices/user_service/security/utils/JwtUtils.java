package com.microservices.user_service.security.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    @Value("${jwtCookie}")
    private String jwtCookie;

    @Value("${jwtSecret}")
    private String jwtSecret;

    @Value("${jwtExpirationMs}")
    private Integer jwtExpirationMs;

    // generate key
    Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64URL.decode(jwtSecret)
        );
    }

    // create jwt from username
    public String generateJwtFromUsername(String username) {
      return Jwts
              .builder()
              .subject(username)
              .issuedAt(new Date())
              .expiration(new Date(new Date().getTime() + jwtExpirationMs))
              .signWith(key())
              .compact();
    }

    // get cookie from jwt
    public ResponseCookie getJwtCookie(String username) {
        return ResponseCookie.from(
                jwtCookie, generateJwtFromUsername(username)
        )
                .path("/api")
                .httpOnly(false)
                .maxAge(24 * 60 * 60)
                .build();
    }

    // fetch username from jwt
    public String getUsernameFromJwt(String jwt) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }

    // clean cookie
    public ResponseCookie cleanCookie() {
        return ResponseCookie.from(jwtCookie, null)
                .path("/api")
                .build();
    }

    //validate jwt
    public boolean validate(String jwt) {
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt);
            return true;
        } catch(RuntimeException e) {}

        return false;
    }

}
