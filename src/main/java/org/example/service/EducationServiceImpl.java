package org.example.service;

import org.example.model.Education;
import org.example.repository.EducationRepositoryImpl;
import org.example.request.EducationRequest;
import org.example.response.EducationResponse;

import java.time.LocalDate;
import java.util.List;

public class EducationServiceImpl implements EducationService {
    private final EducationRepositoryImpl repository;

    public EducationServiceImpl(EducationRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void addEducation(EducationRequest request, int userId) {
        LocalDate startDate = request.getStartDate();
        String description = request.getDescription();
        String university = request.getUniversity();
        int degreeId = request.getDegreeId();
        LocalDate endDate = request.getEndDate();
        Education education = new Education();
        education.setStartDate(startDate);
        education.setDescription(description);
        education.setDegreeId(degreeId);
        education.setUserId(userId);
        education.setUniversity(university);
        education.setEndDate(endDate);
        repository.create(education);
    }

    @Override
    public EducationResponse getAllEducations(int userId) {
        EducationResponse response = new EducationResponse();
        List<Education> educationList = repository.getAllEducations(userId);
        response.setEducationList(educationList);
        return response;
    }
}
