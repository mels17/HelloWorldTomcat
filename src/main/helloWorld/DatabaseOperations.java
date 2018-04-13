package helloWorld;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations implements Repository {

    private Connection connection;

    public DatabaseOperations() throws SQLException, ClassNotFoundException {
        this.connection = DatabaseInitialization.init("worldnames", "admin", "password");
    }

    public List<String> getAllNames() throws Exception {
        checkConnection();
        Statement statement = null;
        List<String> allNames = new ArrayList<String>();
        statement = connection.createStatement();

        ResultSet rs = statement.executeQuery("SELECT * FROM NAMES;");
        while (rs.next()) {
            allNames.add(rs.getString("NAME"));
        }
        rs.close();
        statement.close();

        return allNames;
    }

    public List<String> addName(String name) throws Exception {
        checkConnection();
        PreparedStatement preparedStatement = null;

        preparedStatement = connection.prepareStatement("INSERT INTO NAMES(NAME) VALUES (?)");
        if (!getAllNames().contains(name) && !name.isEmpty()) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();

        return getAllNames();
    }

    public List<String> deleteName(String name) throws Exception {
        checkConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("DELETE FROM NAMES WHERE NAME = ?");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getAllNames();
    }

    public void checkConnection() throws Exception {
        if (connection == null) {
            throw new Exception("Database connection not established.");
        }
    }

    public void closeConnections() throws SQLException {
        DatabaseInitialization.closeDatabaseConnection(connection);
    }
}
