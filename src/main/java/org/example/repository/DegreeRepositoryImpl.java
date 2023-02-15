package org.example.repository;

import org.example.model.Degree;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DegreeRepositoryImpl implements BaseRepository<Degree> {
    private final Connection connection;
    private ResultSet resultSet;

    private PreparedStatement preparedStatement;

    public DegreeRepositoryImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Degree> getAll() {
        List<Degree> degreeList = new ArrayList<>();
        try {
            String getAllDegreeQuery = "Select * from degree";
            preparedStatement = connection.prepareStatement(getAllDegreeQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Degree result = returnDegree();
                degreeList.add(result);
            }
            return degreeList;

        } catch (SQLException e) {
            System.out.println("Get All degree error message - " + e.getMessage());
        }
        return null;
    }

    @Override
    public Degree getById(int id) {
        try {
            String getByIdQuery = "Select * from degree where e_degree_id=? ";
            preparedStatement = connection.prepareStatement(getByIdQuery);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            System.out.println("Degree set - " + resultSet.next());

            while (resultSet.next()) {
                return returnDegree();
            }

        } catch (SQLException e) {
            System.out.println("Get degree by id error message-" + e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public void update(Degree degree) {

    }

    @Override
    public void create(Degree degree) {

    }

    private Degree returnDegree() throws SQLException {
        int id = resultSet.getInt("e_degree_id");
        String name = resultSet.getString("degree_type");
        return new Degree(id, name);
    }
}
