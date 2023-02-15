package org.example.controllers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.example.manager.TokenManager;
import org.example.request.SkillRequest;
import org.example.response.SkillResponse;
import org.example.service.SkillService;
import org.example.utils.AuthorizeTokenImpl;
import org.example.utils.RequestValidationCheck;
import org.example.utils.RequestValidator;
import org.json.simple.JSONObject;

public class SkillController {
    private final SkillService service;

    private final TokenManager manager;

    public SkillController(SkillService service, TokenManager manager) {
        this.service = service;
        this.manager = manager;
    }

    public void createSkill(Context context) {
        SkillRequest request = context.bodyAsClass(SkillRequest.class);
        RequestValidator.checkRequest(request);
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        JSONObject jsonObject = new JSONObject();
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            service.addSkill(request, authorizedUserId);
            jsonObject.put("message", "Created");
            context.json(jsonObject);
        } catch (Exception e) {
            throw new BadRequestResponse();
        }

    }

    public void deleteSkill(Context context) {
        SkillRequest request = context.bodyAsClass(SkillRequest.class);
        RequestValidationCheck.validationCheck(request);
        service.deleteSkill(request);
    }

    public void getAllUserSkills(Context context) {
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            SkillResponse result = service.getAllSkill(authorizedUserId);
            context.res.setStatus(200);
            context.result("OK");
            context.json(result);
        } catch (Exception e) {
            throw new BadRequestResponse("Can not get data from DB:");
        }
    }

}
