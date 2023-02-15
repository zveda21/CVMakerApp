package org.example.repository;

import org.example.model.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageRepositoryImpl implements BaseRepository<Language> {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public LanguageRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Language> getAllLanguagesList(int userId) {

        List<Language> languageList = new ArrayList<>();
        try {
            // this function is used to get All languages of User
            String getAllLanguagesQuery = "SELECT * from language where language.user_id=?";
            preparedStatement = connection.prepareStatement(getAllLanguagesQuery);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Language result = returnLanguage();
                languageList.add(result);
            }
            return languageList;

        } catch (SQLException e) {
            System.out.println("Get All languages error message:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Language> getAll() {
        return null;
    }

    @Override
    public Language getById(int id) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public void update(Language language) {

    }

    @Override
    public void create(Language language) {

        try {
            String createLanguageQuery = "INSERT INTO language(name,user_id) values (?,?)";
            preparedStatement = connection.prepareStatement(createLanguageQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, language.getName());
            preparedStatement.setInt(2, language.getUserId());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                language.setId(id);
            }
        } catch (SQLException e) {
            System.out.println("Language adding error--" + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Language returnLanguage() throws SQLException {
        Language language = new Language();
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int userId = resultSet.getInt("user_id");
        language.setId(id);
        language.setName(name);
        language.setUserId(userId);
        return language;
    }
}
