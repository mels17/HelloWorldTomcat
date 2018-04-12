package helloWorld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.Arrays;

public class DatabaseControllerClassTest {

    DatabaseController dbController;
    MockDatabaseOperationsClass mockDBOps = new MockDatabaseOperationsClass();

    @Before
    public void setUpDbControllerObject() {
//        dbController = new DatabaseController((Connection)new MockConnectionClass(), (DatabaseOperations)mockDBOps);
    }


    @Test
    public void givenAListOfStringReturnsStringSeparatedByComma() throws SQLException {
//        mockDBOps.setList(Arrays.asList("Mel", "Rose"));
//        String expectedResult = "Mel, Rose";
//        Assert.assertEquals(expectedResult, dbController.getAllNames());
    }

}
