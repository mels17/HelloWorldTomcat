package helloWorld;
// Checking
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseController implements Repository {

    private Connection connection;

    public DatabaseController() throws SQLException, ClassNotFoundException {
        this.connection = DatabaseInitialization.init("worldnames", "admin", "password");
    }

    public List<String> getAllNames() throws DatabaseDisconnectedException, SQLException {
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

    public List<String> addName(String name) throws SQLException, DatabaseDisconnectedException {
        checkConnection();
        PreparedStatement preparedStatement = null;

        preparedStatement = connection.prepareStatement("INSERT INTO NAMES(NAME) VALUES (?);");
        if (!getAllNames().contains(name) && !name.isEmpty()) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();

        return getAllNames();
    }

    public List<String> deleteName(String name) throws DatabaseDisconnectedException, SQLException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("DELETE FROM NAMES WHERE NAME = ?;");
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getAllNames();
    }

    @Override
    public List<String> updateName(String oldName, String newName) throws SQLException, DatabaseDisconnectedException {
        checkConnection();
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("UPDATE NAMES SET NAME = ? WHERE ID = (SELECT ID FROM NAMES WHERE NAME = ?);");
        preparedStatement.setString(1, newName);
        preparedStatement.setString(2, oldName);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getAllNames();
    }

    public void checkConnection() throws DatabaseDisconnectedException {
        if (connection == null) {
            throw new DatabaseDisconnectedException("Database connection not established.");
        }
    }

    public void closeConnections() throws SQLException {
        DatabaseInitialization.closeDatabaseConnection(connection);
    }
}
