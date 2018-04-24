package helloWorld;

import java.sql.SQLException;
import java.util.List;

public interface Repository {
    List<String> getAllNames() throws Exception;
    List<String> addName(String name) throws Exception;
    List<String> deleteName(String name) throws Exception;
    List<String> updateName(String oldName, String newName) throws SQLException, DatabaseDisconnectedException;
}
