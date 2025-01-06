package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnexionDB {

    private static SingletonConnexionDB instance;
    private final Connection connection;

    private static final String DB_NAME = "ExamenJava";
    private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private SingletonConnexionDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static SingletonConnexionDB getInstance() {
        if (instance == null) {
            synchronized (SingletonConnexionDB.class) {
                if (instance == null) {
                    instance = new SingletonConnexionDB();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}

