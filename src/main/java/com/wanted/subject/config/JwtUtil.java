package com.wanted.subject.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserDetailsService userDetailsService;
    private final String SECRET_KEY = Base64.getEncoder().encodeToString("gogowantedInternship".getBytes());
    private final long EXPIRATION_TIME = 86400000;

    public String generateToken(String email){
        try {
            Date now = new Date();
            Date expire = new Date(now.getTime() + EXPIRATION_TIME);

            return JWT.create()
                    .withSubject(email)
                    .withIssuedAt(now)
                    .withClaim("email", email)
                    .withExpiresAt(expire)
                    .withIssuer("yeoooo")
                    .sign(Algorithm.HMAC512(SECRET_KEY));
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("토큰을 생성할 수 없습니다.", new RuntimeException());
        }
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                    .withIssuer("yeoooo")
                    .acceptExpiresAt(300)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;

        } catch (JWTVerificationException exception) {
            System.out.println("JWT Verification Failed = " + exception.getMessage());
            return false;
        }
    }

    public String getToken(HttpServletRequest req) {
        return req.getHeader("Authorization");
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserEmail(String token) {
        return JWT.decode(token).getSubject();
    }
}
