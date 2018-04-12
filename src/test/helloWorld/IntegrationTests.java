package helloWorld;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Assert;
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
        String date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        String time = new SimpleDateFormat("h:mm a").format(new Date()).replace("AM", "am").
                replace("PM", "pm");
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
    public void returnsStringWithNewNameAnd200StatusCodeWhenPostRequest() throws ServletException, IOException  {
        String date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        String time = new SimpleDateFormat("h:mm a").format(new Date()).replace("AM", "am").
                replace("PM", "pm");
        String expectedResult = "Hello Mel, Rose, Bob - the time on the server is " + time + " on " + date;
        int expectedStatusCode = 201;
        StringBuffer content = new StringBuffer();
        URL url = new URL("http://localhost:8000");
        String inputLine;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Bob");

        connection.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

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
    public void whenDeleteRequestReturnStringWithoutThatNameAndStatusCode200() throws ServletException, IOException  {
        String date = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
        String time = new SimpleDateFormat("h:mm a").format(new Date()).replace("AM", "am").
                replace("PM", "pm");
        String expectedResult = "Hello Mel, Rose - the time on the server is " + time + " on " + date;
        int expectedStatusCode = 201;
        StringBuffer content = new StringBuffer();
        URL url = new URL("http://localhost:8000");
        String inputLine;
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setDoOutput(true);
        connection.setRequestMethod("DELETE");
        connection.connect();

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "Bob");

        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
        out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
        out.flush();
        out.close();

        System.out.println("REached here.");

        int status = connection.getResponseCode();

        System.out.println("here");
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream())
            );
            while((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }


            in.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        connection.disconnect();


        Assert.assertEquals(expectedStatusCode, status);
        Assert.assertEquals(expectedResult, content.toString());
    }
}
