package org.example.repository;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository<User> extends BaseRepository<User> {
    @Override
    List<User> getAll();

    @Override
    User getById(int id);

    @Override
    int delete(int id);

    @Override
    void update(User user);

    @Override
    void create(User user);

    int signup(User user) throws SQLException;

    User login(String username, String password) throws SQLException;

}
