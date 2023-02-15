package org.example.repository;

import org.example.model.Education;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EducationRepositoryImpl implements BaseRepository<Education> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public EducationRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Education> getAll() {

        List<Education> educationList = new ArrayList<>();
        try {
            // this function is used to get All education + degree type
            String getAllDegreeQuery = "SELECT id,university, description,degree_id, start_date,end_date,user_id from education";
            preparedStatement = connection.prepareStatement(getAllDegreeQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Education result = returnEducation();
                educationList.add(result);
            }
            return educationList;

        } catch (SQLException e) {
            System.out.println("Get All education error message-" + e.getMessage());
        }
        return null;
    }

    public List<Education> getAllEducations(int userId) {

        List<Education> educationList = new ArrayList<>();
        try {
            // this function is used to get All education of User + degree type
            String getAllDegreeQuery = "SELECT education.id,user_id,degree_id,start_date,end_date,degree_type,university,description from education  INNER JOIN users u on u.id = education.user_id inner join degree d on d.e_degree_id = education.degree_id where user_id = ?";
            preparedStatement = connection.prepareStatement(getAllDegreeQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Education result = returnEducation();
                educationList.add(result);
            }
            return educationList;

        } catch (SQLException e) {
            System.out.println("Get All education error message-" + e.getMessage());
        }
        return null;
    }

    @Override
    public Education getById(int id) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public void update(Education education) {

    }

    @Override
    public void create(Education education) {
        try {
            String insertQuery = "INSERT INTO education (start_date,description,degree_id,user_id,end_date,university) values (?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, Date.valueOf(education.getStartDate()));
            preparedStatement.setString(2, education.getDescription());
            preparedStatement.setInt(3, education.getDegreeId());
            preparedStatement.setInt(4, education.getUserId());
            preparedStatement.setDate(5, Date.valueOf(education.getEndDate()));
            preparedStatement.setString(6, education.getUniversity());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                education.setId(id);
            }
        } catch (SQLException e) {
            System.out.println("Adding error" + e.getMessage());
        }
    }

    private Education returnEducation() throws SQLException {
        String university = resultSet.getString("university");
        LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        System.out.println("startDate---" + startDate);
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        int degreeId = resultSet.getInt("degree_id");
        String description = resultSet.getString("description");
        LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        String degreeType = resultSet.getString("degree_type");
        Education education = new Education();
        education.setId(id);
        education.setUserId(userId);
        education.setDegreeId(degreeId);
        education.setStartDate(startDate);
        education.setEndDate(endDate);
        education.setUniversity(university);
        education.setDescription(description);
        education.setDegreeType(degreeType);
        return education;
    }
}
