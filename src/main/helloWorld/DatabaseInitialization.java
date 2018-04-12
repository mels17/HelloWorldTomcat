package helloWorld;

import java.sql.*;

public class DatabaseInitialization {

    public static Connection init() {
        Connection connection = null;

        System.out.print("Connecting to database...");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/worldnames",
                            "admin", "password");
            System.out.println("Database connection successful.");
        } catch (Exception e) {
            System.out.println("Database connection failed...");
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return connection;
    }

    public static void closeDatabaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

