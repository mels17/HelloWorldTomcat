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
}
