package helloWorld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInitialization {
    public static Connection init() throws ClassNotFoundException, SQLException {
        Connection connection = null;

        System.out.println("Connecting to database...");
        Class.forName("org.postgresql.Driver");
        connection = DriverManager
                .getConnection("jdbc:postgresql://localhost:5432/worldnames",
                        "admin", "password");
        System.out.println("Database connection successful.");
        return connection;
    }

    public static void closeDatabaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

