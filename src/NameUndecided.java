import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

public class NameUndecided {

    static Connection connection = DatabaseInitialization.init();
    static DatabaseOperations dbOps = new DatabaseOperations(connection);

    static final String ETERNAL_NAME = "Mel";

    private static String getCurrentDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return dateFormat.format(new Date());
    }

    private static String getCurrentTimeAsString() {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(new Date()).replace("AM", "am").replace("PM", "pm");
    }

    public static String getAllNames() throws SQLException {
        return (dbOps.getAllNames())
                .stream()
                .collect(Collectors.joining(", "));
    }

    public static String getOutputString() throws SQLException {
        return "Hello " +  getAllNames() + " - the time on the server is " + getCurrentTimeAsString() + " on " +
                getCurrentDateAsString();
    }

    public static void closeConnections() throws SQLException {
        DatabaseInitialization.closeDatabaseConnection(connection);
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

//    public static void main(String... args) throws SQLException {
//        System.out.println(getAllNames());
//    }
}
