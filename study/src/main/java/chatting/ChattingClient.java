package chatting;

import java.io.*;
import java.net.Socket;

public class ChattingClient implements Runnable {

    public void run(){
        try {
            Socket socket = new Socket("localhost",9090);
            System.out.println("서버에 접속되었습니다.");
            System.out.println("채팅을 시작합니다. 종료: \"quit\" 입력");

            new Thread(()->{
                try {
                    sendMsg(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(()->{
                try {
                    recieveMsg(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void sendMsg(OutputStream out) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(out);

        String line = "";
        
        while((line = br.readLine()) != null){ //키보드로부터 한줄 읽고
            if("quit".equals(line)){
                break;
            }
            pw.println(line); //한줄 보내기
            pw.flush();
        }
        pw.close();
        br.close();
    }

    public void recieveMsg(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String line = "";

        while((line = br.readLine()) != null){
            if("quit".equals(line)){
                System.out.println("접속 종료");
                break;
            }
            System.out.println(line);
        }
        br.close();
    }
}