package helloWorld;

import java.net.ConnectException;

public class DatabaseDisconnectedException extends ConnectException {
    public DatabaseDisconnectedException(String message) {
        super(message);
    }
}
