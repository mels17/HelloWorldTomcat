package helloWorld;

import java.net.ConnectException;

// Before connectException but maybe? closed channel exception
public class DatabaseDisconnectedException extends ConnectException {
    public DatabaseDisconnectedException(String message) {
        super(message);
    }
}
