package chatting;

public class ChatClientMain {
    public static void main(String[] args) {
        ChattingClient client = new ChattingClient();
        new Thread(client).start();
    }
}
