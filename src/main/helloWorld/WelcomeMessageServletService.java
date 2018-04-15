package helloWorld;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class WelcomeMessageServletService {
//
//    private DatabaseController dbController;
//    private String error = "";
//
//    private static final HashMap statusCodes = new HashMap<String, Integer>();
//    static {
//        statusCodes.put("get", 200);
//        statusCodes.put("post", 201);
//        statusCodes.put("delete", 200);
//    }
//
//    HashMap errorMessages = new HashMap<String, String>();
//
//    private void populateWithDefaultValues() {
//
//
//        errorMessages.put("get", "Cannot access database - Get welcome message request denied.");
//        errorMessages.put("post", "Cannot access database - Post welcome message request denied.");
//        errorMessages.put("delete", "Cannot access database - Delete welcome message request denied.");
//    }
//
//    public WelcomeMessageServletService() {
//        populateWithDefaultValues();
//        try {
//            dbController = new DatabaseController(new DatabaseOperations());
//        } catch (Exception e) {
//            error = "Cannot connect to database.";
//            statusCodes.computeIfPresent("get", (k, v) -> 500);
//            statusCodes.computeIfPresent("post", (k, v) -> 500);
//            statusCodes.computeIfPresent("delete", (k, v) -> 500);
//        }
//    }

    public static ServiceResult getName(DatabaseController dbController) {
        ServiceResult result = new ServiceResult();
        String responseString = "";
        int statusCode = 500;
        try {
            responseString = dbController.getOutputString(new Date());
            statusCode = 200;
        } catch (SQLException e) {
            responseString = "Get request failed: Database Not Found.";
        } catch (ClassNotFoundException e) {
            responseString = "Get request failed: Database Not Found";
        } catch(DatabaseDisconnectedException e) {
            responseString = "Get request failed: Database disconnected.";
        } catch (Exception e) {
            responseString = e.toString();
        }
        result.setMessage(responseString);
        result.setStatusCode(statusCode);
        return result;
    }

    public static ServiceResult postName(DatabaseController dbController, String name) {
        ServiceResult result = new ServiceResult();
        String responseString = "";
        int statusCode = 500;
        try {
            responseString = dbController.addName(name, new Date());
            statusCode = 201;
        } catch (SQLException e) {
            responseString = "Post request failed: Database Not Found.";
        } catch (ClassNotFoundException e) {
            responseString = "Post request failed: Database Not Found";
        } catch(DatabaseDisconnectedException e) {
            responseString = "Post request failed: Database disconnected.";
        } catch (Exception e) {
            responseString = e.toString();
        }
        result.setMessage(responseString);
        result.setStatusCode(statusCode);
        return result;
    }

    public static ServiceResult deleteName(DatabaseController dbController, String name) {
        ServiceResult result = new ServiceResult();
        String responseString = "";
        int statusCode = 500;
        try {
            responseString = dbController.deleteName(name, new Date());
            statusCode = 200;
        } catch (SQLException e) {
            responseString = "Delete request failed: Database Not Found.";
        } catch (ClassNotFoundException e) {
            responseString = "Delete request failed: Database Not Found";
        } catch(DatabaseDisconnectedException e) {
            responseString = "Delete request failed: Database disconnected.";
        } catch (Exception e) {
            responseString = e.toString();
        }
        result.setMessage(responseString);
        result.setStatusCode(statusCode);
        return result;
    }

//    public String getResponseStringForGivenRequest(String request, String name) {
//        if((int)statusCodes.get(request) == 500) {
//            return error;
//        } else {
//            try {
//                if(request.equals("get")) return dbController.getOutputString(new Date());
//                if(request.equals("post")) return dbController.addName(name, new Date());
//                if(request.equals("delete")) return dbController.deleteName(name, new Date());
//            } catch (Exception e) {
//                statusCodes.computeIfPresent(request, (k, v) -> 500);
//                return (String) errorMessages.get(request);
//            }
//        }
//        return "Request not found.";
//    }
//
//    public int getStatusCodeForGivenRequest(String request) {
//        return (int) statusCodes.get(request);
//    }
}
