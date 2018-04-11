import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return connection;
    }

//    public static void main(String args[]) {
//        Connection connection = null;
//        Statement statement = null;
//        System.out.print("Connecting to database...");
//        try {
//            Class.forName("org.postgresql.Driver");
//            connection = DriverManager
//                    .getConnection("jdbc:postgresql://localhost:5432/worldnames",
//                            "admin", "password");
//            System.out.println("Database connection successful.");
//
//            statement = connection.createStatement();
//
//            ResultSet rs = statement.executeQuery("SELECT * FROM NAMES;");
//            while (rs.next()) {
//                System.out.println(rs.getString("NAME"));
//            }
//
//            rs.close();
//            statement.close();
//
//            System.out.println("Closing down connection...");
//            connection.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println(e.getClass().getName()+": "+e.getMessage());
//            System.exit(0);
//        }
//        System.out.println("Table created successfully.");
//    }

    public static void closeDatabaseConnection(Connection connection) throws SQLException {
        connection.close();
    }
}

