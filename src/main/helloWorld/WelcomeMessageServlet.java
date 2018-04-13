package helloWorld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WelcomeMessage", urlPatterns = {""})
public class WelcomeMessageServlet extends HttpServlet {
    WelcomeMessageServletService service = new WelcomeMessageServletService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        writer.println(service.getResponseStringForGivenRequest("get", ""));

        response.setStatus(service.getStatusCodeForGivenRequest("get"));
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        writer.println(service.getResponseStringForGivenRequest("post", name[0]));

        response.setStatus(service.getStatusCodeForGivenRequest("post"));
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Here");
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        writer.println(service.getResponseStringForGivenRequest("delete", name[0]));

        response.setStatus(service.getStatusCodeForGivenRequest("delete"));
    }
}
