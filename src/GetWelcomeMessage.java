import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.stream.Collectors;

@WebServlet(name = "GetWelcomeMessage", urlPatterns = {""})
public class GetWelcomeMessage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {
            writer.println(NameUndecided.getOutputString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setStatus(200);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {
            writer.println(NameUndecided.addNameToDatabase(name[0]));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setStatus(201);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Here");
        System.out.println(request);
        String[] name = request.getParameterValues("name");
        PrintWriter writer = response.getWriter();

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

//        System.out.println(request.getReader().lines().collect(Collectors.joining()));
        writer.println(name[0]);

    }
}
