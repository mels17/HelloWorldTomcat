package helloWorld;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations implements Repository {

    private Connection connection;

    public DatabaseOperations() {
        this.connection = DatabaseInitialization.init();
    }

    public List<String> getAllNames(){
        Statement statement = null;
        List<String> allNames = new ArrayList<String>();
        try {
            statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM NAMES;");
            while (rs.next()) {
                allNames.add(rs.getString("NAME"));
            }
            rs.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allNames;
    }

    public List<String> addName(String name) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO NAMES(NAME) VALUES (?)");
            if(!getAllNames().contains(name) && !name.isEmpty()) {
                preparedStatement.setString(1, name);
                preparedStatement.executeUpdate();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }
        return getAllNames();
    }

    public List<String> deleteName(String name){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM NAMES WHERE NAME = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<String>();
        }

        return getAllNames();
    }


    public void closeConnections() throws SQLException {
        DatabaseInitialization.closeDatabaseConnection(connection);
    }
}
