package helloWorld;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DatabaseOperationsClassTest {

    DatabaseOperations dbOps;

    @Before
    public void setUp() {
        dbOps = new DatabaseOperations();
    }

    @After
    public void disconnectConnection() throws SQLException {
        dbOps.closeConnections();
    }

    // The database at this point has two entries - Mel and Rose
    @Test
    public void getsAllNamesFromTheDatabaseSuccessfully() {
        List<String> expectedResult = Arrays.asList("Mel", "Rose");

        Assert.assertEquals(expectedResult, dbOps.getAllNames());
    }

    @Test
    public void givenNameReturnsListWithAddedName() {

        List<String> expectedResult = Arrays.asList("Mel", "Rose", "PostName");

        Assert.assertEquals(expectedResult, dbOps.addName("PostName"));
        dbOps.deleteName("PostName");
    }

    @Test
    public void givenNameDeletesThatName() {
        List<String> expectedResult = Arrays.asList("Mel", "Rose");

        dbOps.addName("DeleteName");
        Assert.assertEquals(expectedResult, dbOps.deleteName("DeleteName"));
    }
}
