package org.example.response;

import org.example.model.Education;

import java.time.LocalDate;
import java.util.List;

public class EducationResponse {
    private String university;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private int degreeId;
    private String degreeType;
    private List<Education> educationList;

    public void setEducationList(List<Education> educationList) {
        this.educationList = educationList;
    }

    public List<Education> getEducationList() {
        return educationList;
    }
}
