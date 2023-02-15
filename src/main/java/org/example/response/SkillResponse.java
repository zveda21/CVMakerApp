package org.example.response;

import org.example.model.Skill;

import java.util.List;

public class SkillResponse {
    private String name;
    private List<String> namesOfSkills;
    private List<Skill> skills;

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<String> getNamesOfSkills() {
        return namesOfSkills;
    }


    public void setNamesOfSkills(List<String> namesOfSkills) {
        this.namesOfSkills = namesOfSkills;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
