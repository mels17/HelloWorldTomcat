package helloWorld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WelcomeMessageServletServiceClassTest {
    MockDatabaseService controller;

    @Before
    public void setUpController() {
        controller = new MockDatabaseService();
    }

    @Test
    public void returnOutputAsAServiceResultContainingResponseAndStatusCodeSuccessfullyWhenGetRequest() {
        controller.setOutput("NameOne NameTwo");
        ServiceResult actualResult = WelcomeMessageServletService.getName(controller);
        Assert.assertEquals("NameOne NameTwo",actualResult.getMessage());
        Assert.assertEquals(200,actualResult.getStatusCode());
    }

    @Test
    public void returnOutputAsAServiceResultContainingResponseAndStatusCodeSuccessfullyWhenPostRequest() {
        controller.setOutput("NameOne NameTwo");
        ServiceResult actualResult = WelcomeMessageServletService.postName(controller, "Test");
        Assert.assertEquals("NameOne NameTwo",actualResult.getMessage());
        Assert.assertEquals(201,actualResult.getStatusCode());
    }

    @Test
    public void returnOutputAsAServiceResultContainingResponseAndStatusCodeSuccessfullyWhenDeleteRequest() {
        controller.setOutput("NameOne NameTwo");
        ServiceResult actualResult = WelcomeMessageServletService.deleteName(controller, "Test");
        Assert.assertEquals("NameOne NameTwo",actualResult.getMessage());
        Assert.assertEquals(200,actualResult.getStatusCode());
    }

    @Test
    public void returnOutputAsAServiceResultContainingResponseAndStatusCodeSuccessfullyWhenPutRequest() {
        controller.setOutput("NameOne UpdatedName");
        ServiceResult actualResult = WelcomeMessageServletService.updateName(controller, "Test", "Updated Test");
        Assert.assertEquals("NameOne UpdatedName", actualResult.getMessage());
        Assert.assertEquals(200, actualResult.getStatusCode());
    }

    @Test
    public void returnsServiceResultWithErrorMessageAnd500StatusCodeWhenSqlExceptionThrown() {
        controller.setOutput("1");
        ServiceResult actualGetResult = WelcomeMessageServletService.getName(controller);
        ServiceResult actualPostResult = WelcomeMessageServletService.postName(controller, "Test");
        ServiceResult actualDeleteResult = WelcomeMessageServletService.deleteName(controller, "Test");
        ServiceResult actualPutResult = WelcomeMessageServletService.updateName(controller, "Test", "Updated Test");
        Assert.assertEquals("Get request failed: Database Not Found.",actualGetResult.getMessage());
        Assert.assertEquals("Post request failed: Database Not Found.",actualPostResult.getMessage());
        Assert.assertEquals("Delete request failed: Database Not Found.",actualDeleteResult.getMessage());
        Assert.assertEquals("Update request failed: Database Not Found.",actualPutResult.getMessage());
        Assert.assertEquals(500,actualDeleteResult.getStatusCode());
        Assert.assertEquals(500,actualGetResult.getStatusCode());
        Assert.assertEquals(500,actualPostResult.getStatusCode());
        Assert.assertEquals(500,actualPutResult.getStatusCode());
    }

    @Test
    public void returnsServiceResultWithErrorMessageAnd500StatusCodeWhenClassNotFoundExceptionThrown() {
        controller.setOutput("2");
        ServiceResult actualGetResult = WelcomeMessageServletService.getName(controller);
        ServiceResult actualPostResult = WelcomeMessageServletService.postName(controller, "Test");
        ServiceResult actualDeleteResult = WelcomeMessageServletService.deleteName(controller, "Test");
        ServiceResult actualPutResult = WelcomeMessageServletService.updateName(controller, "Test", "Updated Test");
        Assert.assertEquals("Get request failed: Database Not Found.",actualGetResult.getMessage());
        Assert.assertEquals("Post request failed: Database Not Found.",actualPostResult.getMessage());
        Assert.assertEquals("Delete request failed: Database Not Found.",actualDeleteResult.getMessage());
        Assert.assertEquals("Update request failed: Database Not Found.",actualPutResult.getMessage());
        Assert.assertEquals(500,actualDeleteResult.getStatusCode());
        Assert.assertEquals(500,actualGetResult.getStatusCode());
        Assert.assertEquals(500,actualPostResult.getStatusCode());
        Assert.assertEquals(500,actualPutResult.getStatusCode());
    }

    @Test
    public void returnsServiceResultWithErrorMessageAnd500StatusCodeWhenDatabaseDisconnectedExceptionThrown() {
        controller.setOutput("3");
        ServiceResult actualGetResult = WelcomeMessageServletService.getName(controller);
        ServiceResult actualPostResult = WelcomeMessageServletService.postName(controller, "Test");
        ServiceResult actualDeleteResult = WelcomeMessageServletService.deleteName(controller, "Test");
        ServiceResult actualPutResult = WelcomeMessageServletService.updateName(controller, "Test", "UpdatedTest");
        Assert.assertEquals("Get request failed: Database disconnected.",actualGetResult.getMessage());
        Assert.assertEquals("Post request failed: Database disconnected.",actualPostResult.getMessage());
        Assert.assertEquals("Delete request failed: Database disconnected.",actualDeleteResult.getMessage());
        Assert.assertEquals("Update request failed: Database disconnected.",actualPutResult.getMessage());
        Assert.assertEquals(500,actualDeleteResult.getStatusCode());
        Assert.assertEquals(500,actualGetResult.getStatusCode());
        Assert.assertEquals(500,actualPostResult.getStatusCode());
        Assert.assertEquals(500,actualPutResult.getStatusCode());
    }

}
