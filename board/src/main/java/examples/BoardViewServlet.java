package examples;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BoardViewServlet", urlPatterns = "/view")
public class BoardViewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        // no
        String noStr = req.getParameter("no");
        int no = Integer.parseInt(noStr);

        // 게시글 가져오기
        BoardService boardService = BoardService.getBoardService();
        Board board = boardService.getBoard(no);

        req.setAttribute("board", board);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/view.jsp");
        requestDispatcher.forward(req, resp);
    }
}
