package examples;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "BoardViewServlet", urlPatterns = "/view")
public class BoardViewServlet extends HttpServlet {
}
