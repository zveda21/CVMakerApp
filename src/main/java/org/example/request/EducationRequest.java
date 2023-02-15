package org.example.request;

import java.time.LocalDate;

public class EducationRequest implements BaseRequest {
    private LocalDate startDate;
    private String description;
    private int degreeId;
    private LocalDate endDate;
    private String university;


    public LocalDate getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getUniversity() {
        return university;
    }

}
