package examples;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(name = "BoardListServlet", urlPatterns = "/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");

        // 게시글 가져오기
        BoardService boardService = BoardService.getBoardService();
        Iterator<Board> iter = boardService.getBoardList();

        req.setAttribute("iter", iter);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/list.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    public void destroy() {
        BoardService boardService = BoardService.getBoardService();
        boardService.save();
    }
}
