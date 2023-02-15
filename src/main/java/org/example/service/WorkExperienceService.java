package org.example.service;

import org.example.request.WorkExperienceRequest;
import org.example.response.WorkExperienceResponse;

public interface WorkExperienceService {
    void addWorkExperience(WorkExperienceRequest request, int userId);

    void deleteWorkExperience(WorkExperienceRequest request);

    WorkExperienceResponse getAllWorkExperience(int userId);

}
