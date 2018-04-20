package helloWorld;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseInitialization {
    public static Connection init(String dbName, String dbUser, String dbUserPassword) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        System.out.println("Loading driver...");
        Class.forName("org.postgresql.Driver");
        System.out.println("Driver loaded!");
        System.out.println("Connecting to database...");
        // com.postgresql.jdbc.Driver
        // "jdbc:postgresql://localhost:5432/" + dbName, dbUser, dbUserPassword
        connection = DriverManager
                .getConnection("jdbc:postgresql://mel-helloworld-instance.czggd3g4trrj.ap-southeast-2.rds.amazonaws.com:5432/worldNames?user=malavika&password=password");
        System.out.println("Database connection successful.");
        return connection;
    }

    public static void closeDatabaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

