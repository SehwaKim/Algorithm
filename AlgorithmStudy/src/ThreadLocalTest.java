import java.util.Map;

public class ThreadLocalTest {
    public static ThreadLocal tl = new ThreadLocal();
    public static void main(String[] args) {
        tl.set("a string from the main");
        System.out.println("***getStr in Main Thread : "+getStr());

        Thread thread = new Thread(){
            @Override
            public void run() {
                tl.set("a string from a thread");
                System.out.println("***getStr in a thread : "+getStr());
            }
        };

        thread.start();

    }
    public static String getStr(){
        return (String) tl.get();
    }
}
