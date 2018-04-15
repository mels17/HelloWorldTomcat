package helloWorld;

import java.sql.SQLException;
import java.util.Date;

public class WelcomeMessageServletService {
    public static ServiceResult getName(Service dbService) {
        ServiceResult result = new ServiceResult();
        String responseString = "";
        int statusCode = 500;
        try {
            responseString = dbService.getOutputString(new Date());
            statusCode = 200;
        } catch (SQLException e) {
            responseString = "Get request failed: Database Not Found.";
        } catch (ClassNotFoundException e) {
            responseString = "Get request failed: Database Not Found.";
        } catch(DatabaseDisconnectedException e) {
            responseString = "Get request failed: Database disconnected.";
        } catch (Exception e) {
            responseString = e.toString();
        }
        result.setMessage(responseString);
        result.setStatusCode(statusCode);
        return result;
    }

    public static ServiceResult postName(Service dbService, String name) {
        ServiceResult result = new ServiceResult();
        String responseString = "";
        int statusCode = 500;
        try {
            responseString = dbService.addName(name, new Date());
            statusCode = 201;
        } catch (SQLException e) {
            responseString = "Post request failed: Database Not Found.";
        } catch (ClassNotFoundException e) {
            responseString = "Post request failed: Database Not Found.";
        } catch(DatabaseDisconnectedException e) {
            responseString = "Post request failed: Database disconnected.";
        } catch (Exception e) {
            responseString = e.toString();
        }
        result.setMessage(responseString);
        result.setStatusCode(statusCode);
        return result;
    }

    public static ServiceResult deleteName(Service dbService, String name) {
        ServiceResult result = new ServiceResult();
        String responseString = "";
        int statusCode = 500;
        try {
            responseString = dbService.deleteName(name, new Date());
            statusCode = 200;
        } catch (SQLException e) {
            responseString = "Delete request failed: Database Not Found.";
        } catch (ClassNotFoundException e) {
            responseString = "Delete request failed: Database Not Found.";
        } catch(DatabaseDisconnectedException e) {
            responseString = "Delete request failed: Database disconnected.";
        } catch (Exception e) {
            responseString = e.toString();
        }
        result.setMessage(responseString);
        result.setStatusCode(statusCode);
        return result;
    }
}
