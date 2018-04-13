package helloWorld;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class IntegrationTests {

    String date;
    String time;
    @Before
    public void setUp() {
        date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        time = new SimpleDateFormat("h:mm a").format(new Date()).replace("AM", "am").
                replace("PM", "pm");
    }

    @Test
    public void returnListSuccessfullyAndStatusCode200WhenGetRequest() throws ServletException, IOException {
        String expectedResult = "Mel, Rose";
        int expectedStatusCode = 200;
        StringBuffer content = new StringBuffer();
        URL url = new URL("http://localhost:8000/list");
        String inputLine;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );


        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        Assert.assertEquals(expectedStatusCode, status);
        Assert.assertEquals(expectedResult, content.toString());
    }

    @Test
    public void getWelcomeMessageSuccessfullyWithStatusCode200WhenGetRequest() throws ServletException, IOException {
        String expectedResult = "Hello Mel, Rose - the time on the server is " + time + " on " + date;
        int expectedStatusCode = 200;
        StringBuffer content = new StringBuffer();
        URL url = new URL("http://localhost:8000");
        String inputLine;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );


        while((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        Assert.assertEquals(expectedStatusCode, status);
        Assert.assertEquals(expectedResult, content.toString());
    }

    @Test
    public void whenPostRequestReturnListWithNewNameStatusCode201AndDeleteRequestReturnStringWithoutThatNameAndStatusCode200() throws ServletException, IOException  {
        String expectedPostResult = "Hello Mel, Rose, DeleteName - the time on the server is " + time + " on " + date;
        String expectedDeleteResult = "Hello Mel, Rose - the time on the server is " + time + " on " + date;
        int expectedPostStatusCode = 201;
        int expectedDeleteStatusCode = 200;
        StringBuffer postContent = new StringBuffer();

        String inputLine;

        URL postUrl = new URL("http://localhost:8000");
        HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();

        postConnection.setRequestMethod("POST");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "DeleteName");

        postConnection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(postConnection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        int status = postConnection.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(postConnection.getInputStream())
        );


        while((inputLine = in.readLine()) != null) {
            postContent.append(inputLine);
        }

        in.close();

        Assert.assertEquals(expectedPostStatusCode, status);
        Assert.assertEquals(expectedPostResult, postContent.toString());

        postConnection.disconnect();


        /**
         * Test for delete
         */
        URL deleteURL = new URL("http://localhost:8000?name=DeleteName");

        HttpURLConnection deleteConnection = (HttpURLConnection) deleteURL.openConnection();

        deleteConnection.setDoOutput(true);
        deleteConnection.setRequestMethod("DELETE");


        int deleteStatusCode = deleteConnection.getResponseCode();
        StringBuffer deleteContent = new StringBuffer();

        try {
            BufferedReader inDel = new BufferedReader(
                    new InputStreamReader(deleteConnection.getInputStream())
            );
            while((inputLine = inDel.readLine()) != null) {
                deleteContent.append(inputLine);
            }
            inDel.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        deleteConnection.disconnect();


        Assert.assertEquals(expectedDeleteStatusCode, deleteStatusCode);
        Assert.assertEquals(expectedDeleteResult, deleteContent.toString());
    }

    @Test
    public void whenEternalNameDeleteRequestReturnListWithoutAltering() throws IOException {
        URL deleteURL = new URL("http://localhost:8000?name=Mel");
        HttpURLConnection deleteConnection = (HttpURLConnection) deleteURL.openConnection();
        StringBuffer deleteContent = new StringBuffer();
        String inputLine;
        String expectedDeleteOutput = "Hello Mel, Rose - the time on the server is " + time + " on " + date;

        deleteConnection.setDoOutput(true);
        deleteConnection.setRequestMethod("DELETE");

        BufferedReader inputDelete = new BufferedReader(
                new InputStreamReader(deleteConnection.getInputStream())
        );
        while((inputLine = inputDelete.readLine()) != null) {
            deleteContent.append(inputLine);
        }
        inputDelete.close();

        deleteConnection.disconnect();

        Assert.assertEquals(expectedDeleteOutput, deleteContent.toString());
    }
}
