package org.example.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.example.model.Degree;
import org.example.repository.DegreeRepositoryImpl;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GetAllDegreeHandler implements Handler {

    DegreeRepositoryImpl degreeRepository;


    public GetAllDegreeHandler(DegreeRepositoryImpl degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {

        try {
            List<Degree> degreeList = degreeRepository.getAll();
            System.out.println("All degree:");
            context.res.setStatus(200);
            List<JSONObject> jsonObjectList = new ArrayList<>();
            for (Degree degree : degreeList) {
                JSONObject obj = new JSONObject();
                obj.put("id", degree.getId());
                obj.put("type", degree.getName());
                jsonObjectList.add(obj);
            }
            context.json(jsonObjectList);

            context.res.addHeader("Access-Control-Allow-Methods", "*");
            context.res.addHeader("Access-Control-Allow-Origin", "*");

        } catch (Exception e) {
            if (e.getCause() instanceof SQLException) {
                context.res.setStatus(500);
                context.res.getOutputStream().print("Database error happened");
                context.res.getOutputStream().close();
            }
        }
    }
}
