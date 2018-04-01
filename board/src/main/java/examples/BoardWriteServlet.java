package examples;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "BoardWriteServlet", urlPatterns = "/write")
public class BoardWriteServlet extends HttpServlet {
}
