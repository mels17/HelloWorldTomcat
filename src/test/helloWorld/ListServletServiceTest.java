package helloWorld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ListServletServiceTest {

    MockDatabaseService controller;

    @Before
    public void setUpController() {
        controller = new MockDatabaseService();
    }

    @Test
    public void returnOutputAsAServiceResultContainingResponseAndStatusCodeSuccessfully() {
        controller.setOutput("NameOne NameTwo");
        ServiceResult actualResult = ListServletService.getList(controller);
        Assert.assertEquals("NameOne NameTwo",actualResult.getMessage());
        Assert.assertEquals(200,actualResult.getStatusCode());
    }

    @Test
    public void returnsServiceResultWithErrorMessageAnd500StatusCodeWhenSqlExceptionThrown() {
        controller.setOutput("1");
        ServiceResult actualResult = ListServletService.getList(controller);
        Assert.assertEquals("Get request failed: Database not found.",actualResult.getMessage());
        Assert.assertEquals(500,actualResult.getStatusCode());
    }

    @Test
    public void returnsServiceResultWithErrorMessageAnd500StatusCodeWhenClassNotFoundExceptionThrown() {
        controller.setOutput("2");
        ServiceResult actualResult = ListServletService.getList(controller);
        Assert.assertEquals("Get request failed: Database not found.",actualResult.getMessage());
        Assert.assertEquals(500,actualResult.getStatusCode());
    }

    @Test
    public void returnsServiceResultWithErrorMessageAnd500StatusCodeWhenDatabaseDisconnectedExceptionThrown() {
        controller.setOutput("3");
        ServiceResult actualResult = ListServletService.getList(controller);
        Assert.assertEquals("Get request failed: Database disconnected",actualResult.getMessage());
        Assert.assertEquals(500,actualResult.getStatusCode());
    }
}
