package helloWorld;

import java.sql.SQLException;
import java.util.Date;

public class MockDatabaseService implements Service {

    String output;

    @Override
    public String getAllNames() throws Exception {
        return getOutput();
    }

    @Override
    public String getOutputString(Date date) throws Exception {
        return getOutput();
    }

    @Override
    public String addName(String name, Date date) throws Exception {
        return getOutput();
    }

    @Override
    public String deleteName(String name, Date date) throws Exception {
        return getOutput();
    }

    @Override
    public String updateName(String oldName, String newName, Date date) throws Exception {
        return getOutput();
    }

    private String getOutput() throws Exception{
        if(output.equals("1")) throw new SQLException();
        else if(output.equals("2")) throw new ClassNotFoundException();
        else if(output.equals("3")) throw new DatabaseDisconnectedException(output);
        else return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
