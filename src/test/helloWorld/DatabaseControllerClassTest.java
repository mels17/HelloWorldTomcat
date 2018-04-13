package helloWorld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class DatabaseControllerClassTest {

    MockRepositoryClass mockRepo;
    DatabaseController dbController;
    Date date;
    String dateString;
    String timeString;

    @Before
    public void setUp() {
        mockRepo = new MockRepositoryClass();
        dbController = new DatabaseController(mockRepo);
    }

    @Before
    public void setUpTimeDate() {
        date = new Date();
        dateString = new SimpleDateFormat("dd MMMM yyyy")
                .format(date);
        timeString = new SimpleDateFormat("h:mm a")
                .format(date)
                .replace("AM", "am")
                .replace("PM", "pm");
    }

    @Test
    public void getsAllTheNamesAsStringSeperatedByCommaFromTheRepositorySuccessfully() throws Exception {
        String expectedResult = "NameOne, NameTwo";

        mockRepo.setList(Arrays.asList("NameOne", "NameTwo"));

        Assert.assertEquals(expectedResult, dbController.getAllNames());
    }

    @Test
    public void returnsEmptyStringWhenRepoIsEmpty() throws Exception {
        String expectedResult = "";

        mockRepo.setList(new ArrayList<String>());

        Assert.assertEquals(expectedResult, dbController.getAllNames());
    }

    @Test
    public void whenRepoHasJustOneEntryReturnsThatNameWithoutComma() throws Exception {
        String expectedResult = "NameOne";

        mockRepo.setList(Arrays.asList("NameOne"));

        Assert.assertEquals(expectedResult, dbController.getAllNames());
    }

    @Test
    public void whenRepoHasOneNameReturnsFormattedWelcomeMessageSuccessfully() throws Exception {
        String expectedResult = "Hello NameOne - the time on the server is " + timeString +
                " on " + dateString;

        mockRepo.setList(Arrays.asList("NameOne"));

        Assert.assertEquals(expectedResult, dbController.getOutputString(date));
    }

    @Test
    public void whenRepoHasTwoNamesReturnsFormattedWelcomeMessageWithCommasBetweenNamesSuccessfully() throws Exception {
        String expectedResult = "Hello NameOne, NameTwo - the time on the server is " + timeString +
                " on " + dateString;

        mockRepo.setList(Arrays.asList("NameOne", "NameTwo"));

        Assert.assertEquals(expectedResult, dbController.getOutputString(date));
    }

    @Test
    public void whenEmptyRepoReturnsFormattedWelcomeMessageWithoutAnyNames() throws Exception {
        String expectedResult = "Hello  - the time on the server is " + timeString +
                " on " + dateString;

        mockRepo.setList(new ArrayList<String>());

        Assert.assertEquals(expectedResult, dbController.getOutputString(date));
    }

    @Test
    public void whenANameIsAddedToARepoWithOneEntryReturnsTheFormattedStringContainingTheName() throws Exception {
        String expectedResult = "Hello NameOne, NameTwo - the time on the server is " + timeString +
                " on " + dateString;

        mockRepo.setList(Arrays.asList("NameOne", "NameTwo"));

        Assert.assertEquals(expectedResult, dbController.addName("NameTwo", date));
    }

    @Test
    public void whenEmptyStringIsGivenReturnsTheFormattedStringWithTheEntryAlreadyInTheRepo() throws Exception {
        String expectedResult = "Hello NameOne - the time on the server is " + timeString +
                " on " + dateString;

        mockRepo.setList(Arrays.asList("NameOne"));

        Assert.assertEquals(expectedResult, dbController.addName("", date));
    }

    @Test
    public void whenStringGivenReturnsFormattedStringWithoutName() throws Exception {
        String expectedResult = "Hello NameOne - the time on the server is " + timeString +
                " on " + dateString;

        mockRepo.setList(Arrays.asList("NameOne"));

        Assert.assertEquals(expectedResult, dbController.deleteName("NameTwo", date));
    }

    @Test
    public void whenEternalNameGivenReturnsFormattedStringWithoutDeletingIt() throws Exception {
        String expectedResult = "Hello Mel - the time on the server is " + timeString +
                " on " + dateString;

        mockRepo.setList(Arrays.asList("Mel"));

        Assert.assertEquals(expectedResult, dbController.deleteName("Mel", date));
    }
}
