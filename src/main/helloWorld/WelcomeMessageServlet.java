package helloWorld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "WelcomeMessage", urlPatterns = {""})
public class WelcomeMessageServlet extends HttpServlet {

    DatabaseService dbController = new DatabaseService(new DatabaseController());

    public WelcomeMessageServlet() throws SQLException, ClassNotFoundException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        ServiceResult result = WelcomeMessageServletService.getName(dbController);

        writer.println(result.getMessage());
        response.setStatus(result.getStatusCode());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        ServiceResult result = WelcomeMessageServletService.postName(dbController, name[0]);

        writer.println(result.getMessage());
        response.setStatus(result.getStatusCode());
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Here");
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        ServiceResult result = WelcomeMessageServletService.deleteName(dbController, name[0]);

        writer.println(result.getMessage());
        response.setStatus(result.getStatusCode());
    }
}
