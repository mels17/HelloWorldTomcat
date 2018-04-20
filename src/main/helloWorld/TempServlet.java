package helloWorld;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Temp", urlPatterns = {"/temp"})
public class TempServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(204);
    }

}
