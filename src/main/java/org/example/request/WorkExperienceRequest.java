package org.example.request;

import java.time.LocalDate;

public class WorkExperienceRequest implements BaseRequest {

    private String jobTitle;
    private String company;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean currentDate;
    private String description;

    public String getJobTitle() {
        return jobTitle;
    }

    public boolean isCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(boolean currentDate) {
        this.currentDate = currentDate;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
