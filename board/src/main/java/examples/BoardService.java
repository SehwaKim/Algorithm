package examples;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardService {
    private static BoardService boardService = new BoardService();
    private List<Board> list;
    private int no;

    private BoardService(){
        list = new ArrayList<Board>();
        no = 1;
    }

    public static BoardService getBoardService(){
        return boardService;
    }

    public synchronized void addBoard(Board board){
        board.setNo(no++);
        list.add(board);
    }

    public synchronized Iterator<Board> getBoardList(){
        return list.iterator();
    }

    public synchronized Board getBoard(int no){
        for(Board b : list){
            if(b.getNo() == no){
                return b;
            }
        }
        return null;
    }
}
