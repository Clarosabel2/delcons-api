package com.delcons.features.auth.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.delcons.features.auth.exception.TokenException;
import com.delcons.features.user.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String apiSecret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                    .withIssuer("delcons")
                    .withSubject(user.getUsername())
                    .withClaim("id",user.getId())
                    .withClaim("role",user.getRol().name())
                    .withExpiresAt(generateDateExpiration())
                    .sign(algorithm);
        }
        catch (JWTCreationException e) {
            throw new TokenException();
        }
    }

    public String getSubject(String token) {
        if(token == null) throw new TokenException("Token is null");
        DecodedJWT verifier = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret); //validando la firma
            verifier = JWT.require(algorithm)
                    .withIssuer("delcons")
                    .build()
                    .verify(token);
            verifier.getSubject();
        } catch (JWTVerificationException exception) {

            System.out.println(exception.getMessage());
        }
        if (verifier.getSubject() == null) {
            throw new TokenException("Token not found");
        }
        return verifier.getSubject();
    }

    private Instant generateDateExpiration(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
