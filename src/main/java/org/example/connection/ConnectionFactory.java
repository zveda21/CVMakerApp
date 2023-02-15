package org.example.connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private final String schema;
    private final int port;
    private Connection connection = null;
    private final String DATABASE_URL = "jdbc:postgresql://10.212.18.31:";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "123";

    public ConnectionFactory() {
        this("public", 5433);
    }

    public ConnectionFactory(String schema) {
        this(schema, 5433);
    }

    public ConnectionFactory(String schema, int port) {
        this.schema = schema;
        this.port = port;
    }

    public Connection getConnection() throws SQLException {
        if (connection == null) {
            String url = DATABASE_URL + port + "/postgres";
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            connection.setSchema(schema);
            System.out.println("Connected to database, schema: " + schema);
        }
        return connection;
    }
}
