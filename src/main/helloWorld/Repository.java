package helloWorld;

import java.util.List;

public interface Repository {
    List<String> getAllNames();
    List<String> addName(String name);
    List<String> deleteName(String name);
}
