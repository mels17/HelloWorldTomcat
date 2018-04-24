package helloWorld;

import java.util.Date;

public interface Service {
    String getAllNames() throws Exception;
    String getOutputString(Date date) throws Exception;
    String addName(String name, Date date) throws Exception;
    String deleteName(String name, Date date) throws Exception;
    String updateName(String oldName, String newName, Date date) throws Exception;
}
