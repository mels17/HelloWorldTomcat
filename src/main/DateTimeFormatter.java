package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {
    public static String getCurrentDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return dateFormat.format(new Date());
    }

    public static String getCurrentTimeAsString() {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(new Date()).replace("AM", "am").replace("PM", "pm");
    }
}
