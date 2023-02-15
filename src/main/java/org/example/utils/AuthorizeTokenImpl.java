package org.example.utils;

import io.javalin.http.ForbiddenResponse;
import org.example.manager.JwtTokenManagerImpl;
import org.example.manager.TokenManager;

public class AuthorizeTokenImpl {

    public AuthorizeTokenImpl(TokenManager manager) {
        this.manager = manager;
    }

    private TokenManager manager;

    public int authorize(String token) {
        if (token == null) {
            throw new ForbiddenResponse("Token is null:");
        }
        manager = new JwtTokenManagerImpl();
        try {
            return manager.authorize(token);
        } catch (Exception e) {
            throw new ForbiddenResponse();
        }
    }
}
