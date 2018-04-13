package helloWorld;

import java.util.Date;
import java.util.HashMap;

public class WelcomeMessageServletService {

    DatabaseController dbController;
    String error = "";

    HashMap statusCodes = new HashMap<String, Integer>();
    HashMap errorMessages = new HashMap<String, String>();

    private void populateWithDefaultValues() {
        statusCodes.put("get", 200);
        statusCodes.put("post", 201);
        statusCodes.put("delete", 200);

        errorMessages.put("get", "Cannot access database - Get welcome message request denied.");
        errorMessages.put("post", "Cannot access database - Post welcome message request denied.");
        errorMessages.put("delete", "Cannot access database - Delete welcome message request denied.");
    }

    public WelcomeMessageServletService() {
        populateWithDefaultValues();
        try {
            dbController = new DatabaseController(new DatabaseOperations());
        } catch (Exception e) {
            error = "Cannot connect to database.";
            statusCodes.computeIfPresent("get", (k, v) -> 500);
            statusCodes.computeIfPresent("post", (k, v) -> 500);
            statusCodes.computeIfPresent("delete", (k, v) -> 500);
        }
    }

    public String getResponseStringForGivenRequest(String request, String name) {
        if((int)statusCodes.get(request) == 500) {
            return error;
        } else {
            try {
                if(request.equals("get")) return dbController.getOutputString(new Date());
                if(request.equals("post")) return dbController.addName(name, new Date());
                if(request.equals("delete")) return dbController.deleteName(name, new Date());
            } catch (Exception e) {
                statusCodes.computeIfPresent(request, (k, v) -> 500);
                return (String) errorMessages.get(request);
            }
        }
        return "Request not found.";
    }

    public int getStatusCodeForGivenRequest(String request) {
        return (int) statusCodes.get(request);
    }
}
