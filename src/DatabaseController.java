import main.DateTimeFormatter;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class DatabaseController {

    static Connection connection = DatabaseInitialization.init();
    static DatabaseOperations dbOps = new DatabaseOperations(connection);

    static final String ETERNAL_NAME = "Mel";

    public static String getAllNames() throws SQLException {
        return (dbOps.getAllNames())
                .stream()
                .collect(Collectors.joining(", "));
    }

    public static String getOutputString() throws SQLException {
        return "Hello " +  getAllNames() + " - the time on the server is " + DateTimeFormatter.getCurrentTimeAsString() + " on " +
                DateTimeFormatter.getCurrentDateAsString();
    }

    public static String addNameToDatabase(String name) throws SQLException {
        dbOps.addName(name);
        return getOutputString();
    }

    public static String deleteNameFromDatabase(String name) throws SQLException {
        if(!name.equals(ETERNAL_NAME)) {
            dbOps.deleteName(name);
        }
        return getOutputString();
    }

    public static void closeConnections() throws SQLException {
        DatabaseInitialization.closeDatabaseConnection(connection);
    }
}
