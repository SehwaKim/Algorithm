package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChattingServer {
    private static List<Socket> list = new ArrayList<Socket>();

    public static void main(String[] args) throws Exception {
        ServerSocket server = null;
        try{
            server = new ServerSocket(9090);

            Socket client = null;
            while(true) {
                client = server.accept();
                list.add(client);

                new Thread(() -> {
                    try {
                        broadcast(client);
                        recieveMsg(client);
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

    private void broadcast(Socket client) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(client.getOutputStream());
        String line = "";
        while((line = br.readLine()) != null){
            if("quit".equals(line)) {
                break;
            }
            pw.println(line);
            pw.flush();
        }
        pw.close();
        br.close();
    }

    private void recieveMsg(Socket client) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line = "";
        while((line = br.readLine()) != null) {
            if ("quit".equals(line)) {
                break;
            }
            System.out.println(line);
        }
        br.close();
    }
}