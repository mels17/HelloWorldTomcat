package helloWorld;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MockRepositoryClass implements Repository{

    List<String> names = new ArrayList<String>();

    @Override
    public List<String> getAllNames() {
        return names;
    }

    @Override
    public List<String> addName(String name) {
        return names;
    }

    @Override
    public List<String> deleteName(String name) {
        return names;
    }

    @Override
    public List<String> updateName(String oldName, String newName) throws SQLException, DatabaseDisconnectedException {
        return names;
    }

    public void setList(List<String> names) {
        this.names = names;
    }
}
