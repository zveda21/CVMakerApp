package org.example.response;

import org.example.model.WorkExperience;

import java.util.Date;
import java.util.List;

public class WorkExperienceResponse {
    private String jobTitle;
    private String company;
    private Date startDate;
    private Date endDate;
    private boolean isCurrentDate;
    private String description;
    private List<WorkExperience> workExperienceList;

    public void setWorkExperienceList(List<WorkExperience> workExperienceList) {
        this.workExperienceList = workExperienceList;
    }

    public List<WorkExperience> getWorkExperienceList() {
        return workExperienceList;
    }
}
