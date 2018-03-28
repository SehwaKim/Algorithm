package tdd.examples;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FirstTest {
    //테스트 대상은 필드로 선언
    MyUtil myUtil; //같은 tdd.examples 패키지

    @Before
    public void init(){
        myUtil = new MyUtil();
        // 변수 초기화 역할 - 테스트 할때 객체 항상 fresh해야 하니까 before붙여서 항상 테스트 메소드 전에 초기화시키는 용도로 쓴다

        System.out.println("init"); //각각 테스트메소드 실행될때마다 앞에 실행됨
    }
    @Test //테스트 메소드 실행 순서는 상관이 없다 순서대로 실행되지 않는다
    public void test1() throws Exception {
        System.out.println("test1");
    }
    @Test
    public void test2() throws Exception {
        System.out.println("test2");
    }
    @Test
    public void test3() throws Exception {
        System.out.println("test3");
    }

    @After
    public void destroy(){ // init은 초기화, destroy는 뒷정리
        myUtil = null;
        System.out.println("destroy");
    }
}