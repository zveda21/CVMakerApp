package org.example.controllers;

import io.javalin.http.BadRequestResponse;
import io.javalin.http.Context;
import org.example.manager.TokenManager;
import org.example.request.LanguageRequest;
import org.example.response.LanguageResponse;
import org.example.service.LanguageServiceImpl;
import org.example.utils.AuthorizeTokenImpl;
import org.example.utils.RequestValidator;
import org.json.simple.JSONObject;

public class LanguageController {

    private final LanguageServiceImpl languageService;
    private final TokenManager manager;

    public LanguageController(LanguageServiceImpl languageService, TokenManager manager) {
        this.languageService = languageService;
        this.manager = manager;
    }

    public void createLanguage(Context context) {
        LanguageRequest languageRequest = context.bodyAsClass(LanguageRequest.class);
        RequestValidator.checkRequest(languageRequest);
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        JSONObject jsonObject = new JSONObject();
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            languageService.addLanguage(languageRequest, authorizedUserId);
            jsonObject.put("message", "Created");
            context.json(jsonObject);
        } catch (Exception e) {
            throw new BadRequestResponse();
        }
    }

    public void getAllLanguagesOfUser(Context context) {
        AuthorizeTokenImpl authorizeToken = new AuthorizeTokenImpl(manager);
        try {
            int authorizedUserId = authorizeToken.authorize(context.header("Authorization"));
            LanguageResponse result = languageService.getAllLanguages(authorizedUserId);
            context.res.setStatus(200);
            context.result("OK");
            context.json(result.getLanguageList());
        } catch (Exception e) {
            throw new BadRequestResponse("Can not connect,check your token:");
        }
    }
}
