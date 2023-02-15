package org.example.service;

import org.example.request.AuthRequest;
import org.example.response.AuthResponse;

import java.sql.SQLException;

public interface UserService {

    AuthResponse signup(AuthRequest request) throws SQLException;

    AuthResponse login(AuthRequest request);

    void addPersonalInformation(AuthRequest request, int userId);

    AuthResponse getUserPersonalInformation(int userId);

    int authorize(String token);
}