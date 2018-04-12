package helloWorld;

import java.util.List;

public class MockDatabaseOperationsClass extends DatabaseOperations{

    List<String> list;
    public void setList(List<String> list) {
        this.list = list;
    }

    public List<String> getAllNames(MockConnectionClass connection) {
        return list;
    }
}
