package helloWorld;

import java.util.Date;
import java.util.stream.Collectors;

public class DatabaseController {

    Repository repository;
    final String ETERNAL_NAME = "Mel";

    public DatabaseController(Repository repository) {
        this.repository = repository;
    }

    public String getAllNames() {
        return (repository.getAllNames())
                .stream()
                .collect(Collectors.joining(", "));
    }

    public String getOutputString(Date date) {
        return "Hello " +  getAllNames() + " - the time on the server is " + DateTimeFormatter.getCurrentTimeAsString(date) + " on " +
                DateTimeFormatter.getCurrentDateAsString(date);
    }

    public String addName(String name, Date date) {
        repository.addName(name);
        return getOutputString(date);
    }

    public String deleteName(String name, Date date) {
        if(!name.equals(ETERNAL_NAME)) {
            repository.deleteName(name);
        }
        return getOutputString(date);
    }
}
