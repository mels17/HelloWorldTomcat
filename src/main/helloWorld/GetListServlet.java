package helloWorld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetNameServlet", urlPatterns = {"/list"})
public class GetListServlet extends HttpServlet {
    DatabaseController dbController = new DatabaseController(DatabaseInitialization.init());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        writer.println(dbController.getAllNames());

        response.setStatus(200);
    }
}
