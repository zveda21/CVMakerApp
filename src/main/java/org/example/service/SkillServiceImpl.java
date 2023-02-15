package org.example.service;

import org.example.model.Skill;
import org.example.repository.SkillRepositoryImpl;
import org.example.request.SkillRequest;
import org.example.response.SkillResponse;

import java.util.List;

public class SkillServiceImpl implements SkillService {

    private final SkillRepositoryImpl repository;

    public SkillServiceImpl(SkillRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public void addSkill(SkillRequest request, int userId) {
        String skillName = request.getName();
        Skill skill = new Skill();
        skill.setName(skillName);
        skill.setUserId(userId);
        repository.create(skill);
    }

    @Override
    public void deleteSkill(SkillRequest request) {
        int id = request.getId();
        repository.delete(id);
    }

    @Override
    public SkillResponse getAllSkill(int userId) {
        SkillResponse response = new SkillResponse();
        List<Skill> skills = repository.getAllSkill(userId);
        response.setSkills(skills);
        return response;
    }

}
