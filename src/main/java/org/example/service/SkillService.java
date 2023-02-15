package org.example.service;

import org.example.request.SkillRequest;
import org.example.response.SkillResponse;

public interface SkillService {
    void addSkill(SkillRequest request, int userId);

    void deleteSkill(SkillRequest request);

    SkillResponse getAllSkill(int userId);
}
