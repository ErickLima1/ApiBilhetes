package com.ApiBilhete.ApiBilhetes.infra.security;

import com.ApiBilhete.ApiBilhetes.domain.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    @Value("${api.secret.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm= Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60* 1000))
                    .sign(algorithm);
            return token;

        } catch(JWTCreationException exception) {
            throw new RuntimeException("Error Durante Geração de Token", exception);
        }
    }
    public String validateToken(String token) {
        try {
            Algorithm algorithm= Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch(JWTVerificationException exception) {
            return "";
        }
    }
}
