package helloWorld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterTest {

    String todaysDate;
    String timeNow;

    @Before
    public void getCurrentTimeAndDate() {
        todaysDate = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        timeNow = new SimpleDateFormat("h:mm a").format(new Date()).replace("AM", "am").
                replace("PM", "pm");
    }

    @Test
    public void returnsTheFormattedDateStringWhenMonthDayWithoutAbbreviating() {
        String actualString = DateTimeFormatter.getCurrentDateAsString(new Date());
        Assert.assertEquals(todaysDate, actualString);
    }

    @Test
    public void returnsTheFormattedTimeWhenJustHoursMinsGiven() {
        String actualString = DateTimeFormatter.getCurrentTimeAsString(new Date());
        Assert.assertEquals(timeNow, actualString);
    }

}
