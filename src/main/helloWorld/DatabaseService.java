package helloWorld;

import java.util.Date;
import java.util.stream.Collectors;

public class DatabaseService implements Service {

    Repository repository;
    final String ETERNAL_NAME = "Mel";

    public DatabaseService(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String getAllNames() throws Exception {
        return (repository.getAllNames())
                .stream()
                .collect(Collectors.joining(", "));
    }

    @Override
    public String getOutputString(Date date) throws Exception {
        return "Hello " +  getAllNames() + " - the time on the server is " + DateTimeFormatter.getCurrentTimeAsString(date) + " on " +
                DateTimeFormatter.getCurrentDateAsString(date);
    }

    @Override
    public String addName(String name, Date date) throws Exception {
        repository.addName(name);
        return getOutputString(date);
    }

    @Override
    public String deleteName(String name, Date date) throws Exception {
        if(!name.equals(ETERNAL_NAME)) {
            repository.deleteName(name);
        }
        return getOutputString(date);
    }
}
