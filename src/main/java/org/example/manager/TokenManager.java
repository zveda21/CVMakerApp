package org.example.manager;

public interface TokenManager {

    String issueToken(int userId);

    int authorize(String token);

}