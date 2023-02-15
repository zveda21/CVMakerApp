package org.example.controllers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.example.manager.TokenManager;
import org.example.request.EducationRequest;
import org.example.response.EducationResponse;
import org.example.service.EducationService;
import org.example.utils.AuthorizeTokenImpl;
import org.example.utils.RequestValidator;
import org.json.simple.JSONObject;

public class EducationController {
    private final EducationService educationService;
    private final TokenManager manager;

    public EducationController(EducationService educationService, TokenManager manager) {
        this.educationService = educationService;
        this.manager = manager;
    }

    public void createEducation(Context context) {
        EducationRequest educationRequest = context.bodyAsClass(EducationRequest.class);
        RequestValidator.checkRequest(educationRequest);
        JSONObject jsonObject = new JSONObject();
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            educationService.addEducation(educationRequest, authorizedUserId);
            jsonObject.put("message", "Created");
            jsonObject.put("status", "200");
            context.json(jsonObject);
        } catch (Exception e) {
            throw new BadRequestResponse();
        }

    }

    public void getAllEducationOfUser(Context context) {
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            EducationResponse result = educationService.getAllEducations(authorizedUserId);
            context.json(result);
        } catch (Exception e) {
            throw new BadRequestResponse("Can not connect,check your token:");
        }
    }
}
