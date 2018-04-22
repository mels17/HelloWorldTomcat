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
        String dbHostname = System.getProperty("DB_HOSTNAME");
        String dbPort = System.getProperty("DB_PORT");
        String dbUsername = System.getProperty("DB_USERNAME");
        String dbPassword = System.getProperty("DB_PASSWORD");
        String name = System.getProperty("DB_NAME");
        // com.postgresql.jdbc.Driver
        // "jdbc:postgresql://localhost:5432/" + dbName, dbUser, dbUserPassword
        // "jdbc:postgresql://mel-helloworld-instance.czggd3g4trrj.ap-southeast-2.rds.amazonaws.com:5432/worldNames?user=malavika&password=password"

//        String connectionString = "jdbc:postgresql://" + dbHostname + ":" +dbPort + "/" + name + "?user=" + dbUsername + "&password=" + dbPassword;
        connection = DriverManager
                .getConnection("jdbc:postgresql://mel-helloworld-instance.czggd3g4trrj.ap-southeast-2.rds.amazonaws.com:5432/worldNames?user=malavika&password=password");
        return connection;
    }

    public static void closeDatabaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

