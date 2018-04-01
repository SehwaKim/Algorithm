package examples;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BoardService {
    private static BoardService boardService = new BoardService();
    private List<Board> list;
    private int no;

    private BoardService(){
        File file = new File("boards.dat");
        if(file.exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                list = (List) ois.readObject();
                no = list.get(0).getNo() + 1;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{
            list = new ArrayList<Board>();
            no = 1;
        }
    }

    public static BoardService getBoardService(){
        return boardService;
    }

    public synchronized void addBoard(Board board){
        board.setNo(no++);
        list.add(0, board);
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

    public void save(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("boards.dat"));
            oos.writeObject(list);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
