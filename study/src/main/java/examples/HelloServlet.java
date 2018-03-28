package examples;

import java.io.PrintWriter;

public class HelloServlet extends Servlet {
    public HelloServlet(){
        System.out.println("HelloServlet");
    }

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        PrintWriter pw = response.getOut();

        pw.println("HTTP/1.1 200 OK");
        pw.println("Content-Type: text/html");
        pw.println();

        pw.println("<h1>hello servlet</h1>");
        pw.flush();
    }
}
