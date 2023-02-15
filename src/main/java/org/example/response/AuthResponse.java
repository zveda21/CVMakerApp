package org.example.response;

import org.example.model.User;

public class AuthResponse {

    int userId;
    private String token;
    private User user;

    public AuthResponse() {
    }

    public AuthResponse(int userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}