package helloWorld;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "GetNameServlet", urlPatterns = {"/list"})
public class GetListServlet extends HttpServlet {
    DatabaseService dbController = new DatabaseService(new DatabaseController());

    public GetListServlet() throws SQLException, ClassNotFoundException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        ServiceResult result = ListServletService.getList(dbController);
        writer.println(result.getMessage());
        response.setStatus(result.getStatusCode());
    }
}
