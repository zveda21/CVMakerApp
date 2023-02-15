package org.example.repository;

import org.example.model.WorkExperience;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WorkExperienceRepositoryImpl implements BaseRepository<WorkExperience> {

    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public WorkExperienceRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<WorkExperience> getAll() {
        return null;
    }

    public List<WorkExperience> getAllWorkExperience(int userId) {

        List<WorkExperience> workExperienceList = new ArrayList<>();
        try {
            // this function is used to get All work experience of User
            String getAllDegreeQuery = "SELECT work_experience.id,job_title,company,start_date,end_date,description,work_experience.user_id,work_experience.currentDate from work_experience INNER JOIN users on users.id=work_experience.user_id where work_experience.user_id=?";
            preparedStatement = connection.prepareStatement(getAllDegreeQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                WorkExperience result = returnWorkExperience();
                workExperienceList.add(result);
            }
            return workExperienceList;

        } catch (SQLException e) {
            System.out.println("Get All work experience error message-" + e.getMessage());
        }
        return null;
    }

    @Override
    public WorkExperience getById(int id) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public void update(WorkExperience workExperience) {

    }

    @Override
    public void create(WorkExperience workExperience) {
        try {
            String createWorkExperience = "INSERT INTO work_experience(job_title, company, start_date, end_date, description, user_id) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(createWorkExperience, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, workExperience.getJobTitle());
            preparedStatement.setString(2, workExperience.getCompany());
            preparedStatement.setDate(3, Date.valueOf(workExperience.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(workExperience.getStartDate()));
            preparedStatement.setString(5, workExperience.getDescription());
            preparedStatement.setInt(6, workExperience.getUserId());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                workExperience.setId(id);
            }

        } catch (SQLException e) {
            System.out.println("Adding error--" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void createExperienceUntilCurrentDate(WorkExperience workExperience) {
        try {
            String createUntilCurrentDateExperience = "INSERT INTO work_experience(job_title, company, start_date, currentdate, description, user_id) VALUES (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(createUntilCurrentDateExperience, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, workExperience.getJobTitle());
            preparedStatement.setString(2, workExperience.getCompany());
            preparedStatement.setDate(3, Date.valueOf(workExperience.getStartDate()));
            preparedStatement.setBoolean(4, workExperience.isCurrentDate());
            preparedStatement.setString(5, workExperience.getDescription());
            preparedStatement.setInt(6, workExperience.getUserId());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                workExperience.setId(id);
            }

        } catch (SQLException e) {
            System.out.println("Adding error--" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private WorkExperience returnWorkExperience() throws SQLException {
        WorkExperience workExperience;
        String jobTitle = resultSet.getString("job_title");
        String company = resultSet.getString("company");
        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String description = resultSet.getString("description");
        boolean isCurrentDate = resultSet.getBoolean("currentdate");
        workExperience = new WorkExperience();
        if (resultSet.getDate("end_date") != null) {
            LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
            workExperience.setEndDate(endDate);
        } else {
            workExperience.setEndDate(LocalDate.now());
        }
        workExperience.setId(id);
        workExperience.setUserId(userId);
        workExperience.setJobTitle(jobTitle);
        workExperience.setCompany(company);
        workExperience.setStartDate(startDate);
        workExperience.setDescription(description);
        workExperience.setCurrentDate(isCurrentDate);
        return workExperience;
    }
}
