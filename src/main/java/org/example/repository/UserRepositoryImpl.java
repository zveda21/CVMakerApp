package org.example.repository;

import org.example.model.User;

import java.sql.*;
import java.util.List;

public class UserRepositoryImpl implements UserRepository<User> {

    private final Connection connection;
    private ResultSet resultSet;

    private PreparedStatement preparedStatement;

    public UserRepositoryImpl(Connection connection) {

        this.connection = connection;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getById(int id) {
        try {
            String findUserQuery = "SELECT * from users where id=?";
            preparedStatement = connection.prepareStatement(findUserQuery);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return returnUser();
            }

        } catch (SQLException e) {
            System.err.println("DB error: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void create(User user) {

    }

    @Override
    public int signup(User user) {
        String createUserQuery = " Insert into users(username,email,password)  values (?,?,?)";
        int id = 0;
        try {
            preparedStatement = connection.prepareStatement(createUserQuery, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                System.out.println("id-----" + id);
                user.setId(id);
            }

        } catch (SQLException e) {
            System.out.println("Create user error - " + e.getMessage());
        }
        return id;
    }

    @Override
    public User login(String username, String password) {
        String findUserQuery = "SELECT * from users where username=?";
        int id = 0;
        User user = new User();
        try {
            preparedStatement = connection.prepareStatement(findUserQuery);
            preparedStatement.setString(1, username);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                System.out.println("id-----" + id);
                user.setId(id);
                String resultUsername = resultSet.getString("username");
                String resultPassword = resultSet.getString("password");
                user.setUsername(resultUsername);
                user.setPassword(resultPassword);
            }
        } catch (SQLException e) {
            System.out.println("Find user error:  " + e.getMessage());
        }
        return user;
    }

    public void updateUserInformation(User user, int userId) {
        try {
            String updateUserInformation = "UPDATE users SET firstname=?,lastname=?,address=?,phone=?,work_mail=?,position=? WHERE id=?";
            preparedStatement = connection.prepareStatement(updateUserInformation);
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getPhone());
            preparedStatement.setString(5, user.getWorkMail());
            preparedStatement.setString(6, user.getPosition());
            preparedStatement.setInt(7, userId);
            preparedStatement.executeUpdate();

            System.out.println("Update operation done");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User returnUser() throws SQLException {
        int id = resultSet.getInt("id");
        System.out.println("result id===" + id);
        String firstName = resultSet.getString("firstname");
        String userName = resultSet.getString("username");
        String lastName = resultSet.getString("lastname");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        String mail = resultSet.getString("email");
        String workEmail = resultSet.getString("work_mail");
        String position = resultSet.getString("position");
        User user = new User();
        user.setId(id);
        user.setUsername(userName);
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setAddress(address);
        user.setPhone(phone);
        user.setEmail(mail);
        user.setPosition(position);
        user.setPassword("Ches imana (^_^)");
        user.setWorkMail(workEmail);
        return user;
    }
}
