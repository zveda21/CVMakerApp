package org.example.service;


import org.example.request.EducationRequest;
import org.example.response.EducationResponse;

public interface EducationService {

    void addEducation(EducationRequest request, int userId);

    EducationResponse getAllEducations(int userId);

}
