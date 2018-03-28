package examples;

public abstract class Servlet { //메모리에 한번만 올라가게 할 것이다.
    public void init(){ //한번만 실행

    }

    public void service(HttpRequest request, HttpResponse response){ //어떤요청이던 서비스 실행
        if("GET".equals(request.getMethod())){
            doGet(request, response);
        }else if("POST".equals(request.getMethod())){
            doPost(request,response);
        }
    }

    public void destroy(){

    }

    public void doGet(HttpRequest request, HttpResponse response){

    }

    public void doPost(HttpRequest request, HttpResponse response){

    }
}
