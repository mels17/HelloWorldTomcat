package helloWorld;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {
    public static String getCurrentDateAsString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        return dateFormat.format(date);
    }

    public static String getCurrentTimeAsString(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("h:mm a");
        return dateFormat.format(date).replace("AM", "am").replace("PM", "pm");
    }
}
