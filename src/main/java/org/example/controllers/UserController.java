package org.example.controllers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.example.manager.TokenManager;
import org.example.request.AuthRequest;
import org.example.response.AuthResponse;
import org.example.service.UserService;
import org.example.utils.AuthorizeTokenImpl;
import org.example.utils.RequestValidator;
import org.json.simple.JSONObject;

import java.sql.SQLException;

public class UserController {
    private final UserService service;
    private final TokenManager manager;

    public UserController(UserService service, TokenManager manager) {
        this.service = service;
        this.manager = manager;
    }

    public void signup(Context context) throws SQLException {
        AuthRequest request = context.bodyAsClass(AuthRequest.class);
        RequestValidator.checkRequest(request);
        AuthResponse result = service.signup(request);
        context.json(result);
    }

    public void login(Context context) throws SQLException {
        AuthRequest request = context.bodyAsClass(AuthRequest.class);
        RequestValidator.checkRequest(request);
        JSONObject obj = new JSONObject();
        AuthResponse result = service.login(request);
        System.out.println("My user id *********  " + result.getUserId());
        if (result.getUserId() == 0) {
            context.res.setStatus(403);
            obj.put("message", "There is no user with this credentials");
            obj.put("status", "403");
            context.json(obj);
        } else {
            context.res.addHeader("Authorization", "Bearer " + result.getToken());
            obj.put("token", result.getToken());
            context.json(obj);
        }
    }

    public void addPersonalInformation(Context context) {
        AuthRequest authRequest = context.bodyAsClass(AuthRequest.class);
        RequestValidator.checkRequest(authRequest);
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        JSONObject jsonObject = new JSONObject();
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            service.addPersonalInformation(authRequest, authorizedUserId);
            jsonObject.put("message", "Created");
            context.json(jsonObject);
        } catch (Exception e) {
            throw new BadRequestResponse("paho");
        }
    }

    public void getUserPersonalInformation(Context context) {
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            AuthResponse result = service.getUserPersonalInformation(authorizedUserId);
            context.res.setStatus(200);
            context.result("OK");
            context.json(result.getUser());
        } catch (Exception e) {
            throw new BadRequestResponse("Can not connect,check your token:");
        }
    }
}
