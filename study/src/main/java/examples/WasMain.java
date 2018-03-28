package examples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WasMain {
    public static void main(String[] args) {
        WebApplicationServer was = new WebApplicationServer(8080);
        new Thread(was).start();

        /*WasShutdownHook wasShutdownHook = new WasShutdownHook(was);
        Runtime.getRuntime().addShutdownHook(wasShutdownHook); //jvm이 종료될 때 실행할 스레드를 등록한 것!*/
    }
}