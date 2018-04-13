package helloWorld;

import java.sql.SQLException;

public class ListServletService {

    DatabaseController dbController;
    String error = "";
    int statusCode = 200;

    public ListServletService() {
        try {
            dbController = new DatabaseController(new DatabaseOperations());
        } catch (Exception e) {
            error = "Cannot connect to database.";
            statusCode = 500;
        }
    }

    public String getResponseStringForGetListRequest() {
        if(statusCode == 500) {
            return error;
        } else {
            try {
                return dbController.getAllNames();
            } catch (Exception e) {
                statusCode = 500;
                return "Cannot access database - Get List Request Denied";
            }
        }
    }

    public int getStatusCodeForGetListRequest() {
        return statusCode;
    }
}
