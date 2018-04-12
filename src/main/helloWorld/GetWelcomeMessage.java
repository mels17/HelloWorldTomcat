package helloWorld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(name = "GetWelcomeMessage", urlPatterns = {""})
public class GetWelcomeMessage extends HttpServlet {
    DatabaseController dbController = new DatabaseController(new DatabaseOperations());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");


        writer.println(dbController.getOutputString(new Date()));

        response.setStatus(200);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        writer.println(dbController.addName(name[0], new Date()));

        response.setStatus(201);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Here");
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        writer.println(dbController.deleteName(name[0], new Date()));

        response.setStatus(200);
    }
}
