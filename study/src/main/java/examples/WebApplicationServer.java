package examples;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class WebApplicationServer implements Runnable{
    private int port;
    private DefaultServlet defaultServlet;
    private Map<String, RequestMapping> map; // path(key), RequestMapping(value)

    public WebApplicationServer(int port) {
        this.port = port;
        defaultServlet = new DefaultServlet();

        initServlet();

        WasShutdownHook wasShutdownHook = new WasShutdownHook(this);
        // jvm 이 종료될 때 실행할 Thread를 등록한다.
        Runtime.getRuntime().addShutdownHook(wasShutdownHook);
    }

    // Map을 초기화
    private void initServlet(){
        // servlet.properties에서 정보를 읽어들여 map을 초기화한다.
        map = new HashMap<>();
        ClassLoader classLoader = getClass().getClassLoader(); //getClass는 Object의 메소드다
        InputStream propStream =
                classLoader.getResourceAsStream("servlet.properties");

        Properties prop = new Properties();
        try {
            prop.load(propStream);

            Set<String> keys = prop.stringPropertyNames();
            for (String path : keys) {
                String className = prop.getProperty(path);
                RequestMapping mapping = new RequestMapping();
                mapping.setPath(path);
                mapping.setServletClassName(className);

                // class이름을 가지고 인스턴스를 생성한다.
                // Servlet s = new HiServlet();
                Class clazz = Class.forName(className);
                Servlet s = (Servlet)clazz.newInstance();
                mapping.setSerlvet(s);
                s.init();
                map.put(path, mapping);
            }
        }catch(Exception ex){}
    }

    public void destroy(){
        defaultServlet.destroy();
    }

    public void run(){
        ServerSocket listener = null;
        try{
            listener = new ServerSocket(port);
            System.out.println("client를 기다립니다.");
            while(true) {
                Socket client = listener.accept(); // 블러킹 메소드.

//            System.out.println("접속한 client : " + client.toString());

                new Thread(() -> {
                    try {
                        handleSocket(client);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }finally { // finally부분에서 서버소켓을 close한다.
            try {
                listener.close();
            }catch(Exception e){}
        }
    } // run


    private void handleSocket(Socket client) throws IOException {
        OutputStream out = client.getOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out));
        HttpResponse response = new HttpResponse();
        response.setOut(pw);
        response.setOutputStream(out);

        InputStream in = client.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        HttpRequest request = new HttpRequest();
        line = br.readLine();
        String[] firstLineArgs = line.split(" ");
        request.setMethod(firstLineArgs[0]);
        request.setPath(firstLineArgs[1]);

        while((line = br.readLine()) != null){
            if("".equals(line)){ // 헤더를 읽고 빈줄을 만나면
                break;
            }
            String[] headerArray = line.split(" ");
            if(headerArray[0].startsWith("Host:")){
                request.setHost(headerArray[1].trim());
            }else if(headerArray[0].startsWith("Content-Length:")){
                int length = Integer.parseInt(headerArray[1].trim());
                request.setContentLength(length);
            }else if(headerArray[0].startsWith("User-Agent:")){
                request.setUserAgent(line.substring(12));
            }else if(headerArray[0].startsWith("Content-Type")){
                request.setContentType(headerArray[1].trim());
            }
        }
        System.out.println(request);

        // 사용자가 요청한 path가 map에 있으면 map에 있는 Servlet을 실행
        // 없으면 defaultServlet을 실행
        if(map.containsKey(request.getPath())){
            RequestMapping mapping = map.get(request.getPath());
            Servlet servlet = mapping.getSerlvet();
            servlet.service(request, response);
        }else {
            defaultServlet.service(request, response);
        }

        out.close();
        in.close();
        client.close(); // 클라이언트와 접속이 close된다.
    }
}