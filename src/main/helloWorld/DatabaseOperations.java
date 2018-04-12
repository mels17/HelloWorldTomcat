package helloWorld;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    public static List<String> getAllNames(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        List<String> allNames = new ArrayList<String>();

        ResultSet rs = statement.executeQuery("SELECT * FROM NAMES;");
        while (rs.next()) {
            allNames.add(rs.getString("NAME"));
        }

        rs.close();
        statement.close();
        return allNames;
    }

    public static List<String> addName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO NAMES(NAME) VALUES (?)");

        if(!getAllNames(connection).contains(name)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        return getAllNames(connection);
    }

    public static List<String> deleteName(Connection connection, String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM NAMES WHERE NAME = ?");

        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getAllNames(connection);
    }
}
