package org.example.service;

import org.example.model.WorkExperience;
import org.example.repository.WorkExperienceRepositoryImpl;
import org.example.request.WorkExperienceRequest;
import org.example.response.WorkExperienceResponse;

import java.time.LocalDate;
import java.util.List;

public class WorkExperienceServiceImpl implements WorkExperienceService {

    private final WorkExperienceRepositoryImpl repository;

    public WorkExperienceServiceImpl(WorkExperienceRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void addWorkExperience(WorkExperienceRequest request, int userId) {
        LocalDate startDate = request.getStartDate();
        String jotTitle = request.getJobTitle();
        String company = request.getCompany();
        LocalDate endDate = request.getEndDate();
        String description = request.getDescription();
        boolean isCurrentDate = request.isCurrentDate();
        WorkExperience workExperience;
        workExperience = new WorkExperience();
        workExperience.setStartDate(startDate);
        workExperience.setJobTitle(jotTitle);
        workExperience.setCompany(company);
        workExperience.setEndDate(endDate);
        workExperience.setUserId(userId);
        if (isCurrentDate) {
            workExperience.setCurrentDate(true);
            workExperience.setDescription(description);
            repository.createExperienceUntilCurrentDate(workExperience);
        } else {
            workExperience.setEndDate(endDate);
            workExperience.setDescription(description);
            repository.create(workExperience);
        }
    }

    @Override
    public void deleteWorkExperience(WorkExperienceRequest request) {

    }

    @Override
    public WorkExperienceResponse getAllWorkExperience(int userId) {
        WorkExperienceResponse workExperienceResponse = new WorkExperienceResponse();
        List<WorkExperience> workExperienceList = repository.getAllWorkExperience(userId);
        workExperienceResponse.setWorkExperienceList(workExperienceList);
        return workExperienceResponse;
    }
}
