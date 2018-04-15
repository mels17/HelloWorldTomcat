package helloWorld;

import java.sql.SQLException;

public class ListServletService {
    public static ServiceResult getList(DatabaseController dbController) {
        ServiceResult result = new ServiceResult();
        String responseString = "";
        int statusCode = 500;

        try {
            responseString = dbController.getAllNames();
            statusCode = 200;
        } catch(SQLException e) {
            responseString = "Get request failed: Database not found.";
        } catch(ClassNotFoundException e) {
            responseString = "Get request failed: Database not found.";
        } catch(DatabaseDisconnectedException e) {
            responseString = "Get request failed: Database disconnected";
        } catch (Exception e) {
            responseString = e.toString();
        }
        result.setMessage(responseString);
        result.setStatusCode(statusCode);
        return result;
    }
}
