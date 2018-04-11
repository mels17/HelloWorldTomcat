import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    Connection connection;

    public DatabaseOperations(Connection connection) {
        this.connection = connection;
    }

    public List<String> getAllNames() throws SQLException {
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

    public List<String> addName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO NAMES(NAME) VALUES (?)");

        if(!getAllNames().contains(name)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        }
        preparedStatement.close();
        return getAllNames();
    }

    public List<String> deleteName(String name) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM NAMES WHERE NAME = ?");

        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getAllNames();
    }
}
