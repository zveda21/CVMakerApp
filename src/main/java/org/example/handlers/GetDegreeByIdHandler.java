package org.example.handlers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.plugin.json.JavalinJackson;
import org.example.model.Degree;
import org.example.repository.DegreeRepositoryImpl;
import org.example.request.FindDegreeRequest;
import org.jetbrains.annotations.NotNull;


class GetDegreeByIdHandler implements Handler {
    private final DegreeRepositoryImpl degreeRepository;

    public GetDegreeByIdHandler(DegreeRepositoryImpl degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public void handle(@NotNull Context context) throws Exception {
        JavalinJackson jackson = new JavalinJackson();
        FindDegreeRequest request = jackson.fromJsonString(context.body(), FindDegreeRequest.class);

        int id = request.getId();

        try {
            Degree degree = degreeRepository.getById(id);
            context.result("OK");
        } catch (Exception e) {
            System.out.println("Cant find degree by id/name -" + e.getMessage());
        }
    }
}