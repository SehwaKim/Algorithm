package examples;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "BoardWriteServlet", urlPatterns = "/write")
public class BoardWriteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html charset=utf-8");

        // 파라미터 받기
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        String content = req.getParameter("content");

        // 글정보 저장
        Board board = new Board();
        board.setName(name);
        board.setSubject(subject);
        board.setContent(content);
        board.setRegdate(new Date());

        BoardService boardService = BoardService.getBoardService();
        boardService.addBoard(board);

        resp.sendRedirect("/list");
    }
}
