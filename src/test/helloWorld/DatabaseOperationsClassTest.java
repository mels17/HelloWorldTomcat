package helloWorld;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class DatabaseOperationsClassTest {

    DatabaseController dbOps;

    @Before
    public void setUp() throws SQLException, ClassNotFoundException {
        dbOps = new DatabaseController();
    }

    @After
    public void disconnectConnection() throws SQLException {
        dbOps.closeConnections();
    }

    // The database at this point has two entries - Mel and Rose
    @Test
    public void getsAllNamesFromTheDatabaseSuccessfully() throws Exception {
        List<String> expectedResult = Arrays.asList("Mel", "Rose");

        Assert.assertEquals(expectedResult, dbOps.getAllNames());
    }

    @Test
    public void givenNameReturnsListWithAddedName() throws Exception {
        Assert.assertTrue(dbOps.addName("PostName").contains("PostName"));
        dbOps.deleteName("PostName");
    }

    @Test
    public void givenNameDeletesThatName() throws Exception {
        dbOps.addName("DeleteName");
        Assert.assertFalse(dbOps.deleteName("DeleteName").contains("DeleteName"));
    }
}
