package org.example.controllers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.example.manager.TokenManager;
import org.example.request.WorkExperienceRequest;
import org.example.response.WorkExperienceResponse;
import org.example.service.WorkExperienceServiceImpl;
import org.example.utils.AuthorizeTokenImpl;
import org.example.utils.RequestValidator;
import org.json.simple.JSONObject;

public class WorkExperienceController {
    private final WorkExperienceServiceImpl workExperienceService;
    private final TokenManager manager;

    public WorkExperienceController(WorkExperienceServiceImpl workExperienceService, TokenManager manager) {
        this.workExperienceService = workExperienceService;
        this.manager = manager;
    }

    public void createWorkExperience(Context context) {
        WorkExperienceRequest workExperienceRequest = context.bodyAsClass(WorkExperienceRequest.class);
        RequestValidator.checkRequest(workExperienceRequest);
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        JSONObject jsonObject = new JSONObject();
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            workExperienceService.addWorkExperience(workExperienceRequest, authorizedUserId);
            jsonObject.put("message","Created");
            jsonObject.put("status","200");
            context.json(jsonObject);
        } catch (Exception e) {
            throw new BadRequestResponse();
        }
    }
    public void getAllWorkExperienceOfUser(Context context) {
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            WorkExperienceResponse result = workExperienceService.getAllWorkExperience(authorizedUserId);
            context.res.setStatus(200);
            context.result("OK");
            context.json(result);
        } catch (Exception e) {
            throw new BadRequestResponse("Can not connect,check your token:");
        }
    }
}
