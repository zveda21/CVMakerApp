package org.example.manager;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;

import static org.example.constants.SecurityConstants.EXPIRATION_TIME;
import static org.example.constants.SecurityConstants.SECRET;

public class JwtTokenManagerImpl implements TokenManager {


    @Override
    public String issueToken(int userId) {
        String token = JWT.create()
                .withSubject(String.valueOf(userId))
                .withIssuer("auth0")
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));
        System.out.println("token--" + token);
        return token;
    }

    @Override
    public int authorize(String token) {
        try {
            String userId = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                    .withIssuer("auth0")
                    .build()
                    .verify(token.replace("Bearer ", ""))
                    .getSubject();
            return Integer.parseInt(userId);
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new JWTVerificationException("There is no token");
        }
    }
}
