package helloWorld;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DatabaseController {

    DatabaseOperations dbOps;
    Connection connection;
    final String ETERNAL_NAME = "Mel";

    public DatabaseController(Connection connection) {
        this.connection = connection;
    }

    public String getAllNames() {
        try {
            return (dbOps.getAllNames(connection))
                    .stream()
                    .collect(Collectors.joining(", "));
        } catch (SQLException e) {
            return String.valueOf(e.getStackTrace());
        }
    }

    public String getOutputString(Date date) {
        return "Hello " +  getAllNames() + " - the time on the server is " + DateTimeFormatter.getCurrentTimeAsString(date) + " on " +
                DateTimeFormatter.getCurrentDateAsString(date);
    }

    public String addNameToDatabase(String name, Date date) {
        try {
            dbOps.addName(connection, name);
        } catch (SQLException e) {
            return String.valueOf(e.getStackTrace());
        }
        return getOutputString(date);
    }

    public String deleteNameFromDatabase(String name, Date date) {
        if(!name.equals(ETERNAL_NAME)) {
            try {
                dbOps.deleteName(connection, name);
            } catch (SQLException e) {
                return String.valueOf(e.getStackTrace());
            }
        }
        return getOutputString(date);
    }
//
//    public static void closeConnections() throws SQLException {
//        DatabaseInitialization.closeDatabaseConnection(connection);
//    }
}
