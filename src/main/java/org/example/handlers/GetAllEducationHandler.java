package org.example.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.example.manager.JwtTokenManagerImpl;
import org.example.manager.TokenManager;
import org.example.model.Education;
import org.example.repository.EducationRepositoryImpl;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GetAllEducationHandler implements Handler {
    private final EducationRepositoryImpl educationRepository;

    public GetAllEducationHandler(EducationRepositoryImpl educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public void handle(@NotNull Context context) {
        try {
            TokenManager manager = new JwtTokenManagerImpl();
            String token = context.header("Authorization");
            int id = manager.authorize(token);
            List<Education> educationList = educationRepository.getAll();
            System.out.println("All educations:");
            context.res.setStatus(200);
            context.result("OK");

            List<JSONObject> jsonObjectList = new ArrayList<>();

            for (Education education : educationList) {
                JSONObject obj = new JSONObject();
                obj.put("id", education.getId());
                obj.put("university", education.getUniversity());
                obj.put("startDate", education.getStartDate());
                obj.put("description", education.getDescription());
                obj.put("degree", education.getDegreeType());
                obj.put("userId", education.getUserId());
                obj.put("endDate", education.getEndDate());
                jsonObjectList.add(obj);
            }
            context.json(jsonObjectList);
        } catch (Exception e) {
            System.out.println("Error message------" + e.getMessage());
        }
    }
}
