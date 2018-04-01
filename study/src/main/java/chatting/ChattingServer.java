package chatting;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChattingServer {
    private static List<Socket> list = new ArrayList<Socket>();

    public ChattingServer(){
        ServerSocket server = null;
        try{
            server = new ServerSocket(8080);

            Socket client = null;
            while(true) {
                client = server.accept();
                list.add(client);
                InputStream in = client.getInputStream();

                // client 메세지 읽는 스레드 1개 시작
                new Thread(() -> {
                    try {
                        recieveMsg(in);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

        }catch (Exception ex){
            ex.printStackTrace();

        }finally {
            try {
                server.close();
            }catch (Exception e){}
        }
    }

    private synchronized void broadcast(String line) {
        for(Socket socket : list){
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            pw.println(line);
            pw.flush();
        }
    }

    private void recieveMsg(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        /*while((line = br.readLine()) != null) {
            if ("quit".equals(line)) {
                break;
            }
            System.out.println(line);
        }*/
        while ((line = br.readLine()) != null) {
            if ("quit".equals(line)) { // quit 못옴 고쳐야됨
                break;
            }
            System.out.println(line);
            broadcast(line);
        }
        br.close();
    }
}