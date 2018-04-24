package helloWorld;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;

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
        String dbHostname = "jdbc:postgresql://mel-helloworld-instance.czggd3g4trrj.ap-southeast-2.rds.amazonaws.com";
        String dbPort = "5432";
        String dbUsername = System.getenv("DB_USER");
        String dbPassword = System.getenv("DB_PWD");
        String name = "worldNames";
        // com.postgresql.jdbc.Driver
        // "jdbc:postgresql://localhost:5432/" + dbName, dbUser, dbUserPassword
        // "jdbc:postgresql://mel-helloworld-instance.czggd3g4trrj.ap-southeast-2.rds.amazonaws.com:5432/worldNames?user=malavika&password=password"
        connection = DriverManager
                .getConnection(dbHostname + ":" + dbPort + "/" + name + "?user=" + dbUsername + "&password=" + dbPassword);

//        connection = DriverManager
//                .getConnection("jdbc:postgresql://localhost:5432/" + dbName, dbUser, dbUserPassword);


        return connection;
    }

    public static void closeDatabaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

