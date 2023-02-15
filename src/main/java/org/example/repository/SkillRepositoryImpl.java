package org.example.repository;

import org.example.model.Skill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl implements BaseRepository<Skill> {

    private final Connection connection;
    private ResultSet resultSet;

    private PreparedStatement preparedStatement;

    public SkillRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Skill> getAll() {
        return null;
    }

    public List<Skill> getAllSkill(int userId) {
        // This method is used to get all skills that the given user has
        List<Skill> skills = new ArrayList<>();
        try {
            String getAllSkills = "SELECT skill.id, name, skill.user_id from skill inner join users on users.id = skill.user_id where skill.user_id=?";
            preparedStatement = connection.prepareStatement(getAllSkills);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String skillName = resultSet.getString("name");
                int uId = resultSet.getInt("user_id");
                Skill skill = new Skill();
                skill.setId(id);
                skill.setName(skillName);
                skill.setUserId(uId);
                skills.add(skill);
            }
            return skills;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Skill getById(int id) {
        return null;
    }

    @Override
    public int delete(int id) {
        int deletedRow = 0;
        try {
            String deleteQuery = "DELETE from skill where id=?";
            preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);
            deletedRow = preparedStatement.executeUpdate();

            System.out.print("Command deleted: " + deletedRow);

        } catch (Exception e) {
            System.out.println("Delete error message-" + e.getMessage());
        }
        return deletedRow;
    }

    @Override
    public void update(Skill skill) {

    }

    @Override
    public void create(Skill skill) {
        try {
            String insertSkillQuery = "INSERT INTO skill (name, user_id) values (?,?)";
            preparedStatement = connection.prepareStatement(insertSkillQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, skill.getType());
            preparedStatement.setInt(2, skill.getUserId());
            preparedStatement.executeUpdate();

            int id = 0;
            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                System.out.println("id-----" + id);
                skill.setId(id);
            }
        } catch (SQLException e) {
            System.out.println("Adding skill error" + e.getMessage());
        }
    }
}
