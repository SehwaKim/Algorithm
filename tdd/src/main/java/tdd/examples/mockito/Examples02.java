package tdd.examples.mockito;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

// Mocking과 Verification
public class Examples02 {
    public static void main(String[] args) {
        // 인터페이스 뿐만이 아니라 클래스도 mock 할 수 있다.
        LinkedList mockedList = mock(LinkedList.class);

        // stubbing (stubbing안하면 목객체 메소드 호출해도 아무 액션없음)
        when(mockedList.get(0)).thenReturn("first");
        //mockedList.get(0) 호출하면 제대로된 값이 튀어나오는게 아니라 first 나오게 하라
        when(mockedList.get(1)).thenThrow(new RuntimeException());
        //mockedList.get(1) 호출하면 RuntimeException 나오게 하라
        //만약 이 객체가 exception이 발생했을때 내가 만든 객체가 어떻게 처리하는지 그런거 알고싶을 때 쓴다
        //내 의도에 맞게끔 의존되는 객체를 쓰려고 mock쓰는 것


        System.out.println(mockedList.get(0)); // first
//        System.out.println(mockedList.get(1)); // Runtime exception 발생
        System.out.println(mockedList.get(10)); // null

        verify(mockedList).get(0); //get(0)을 호출한적이 있느냐 (마치 녹음기같이?)
    }
}

/*
생성한 Mock의 메소드를 호출하면 아무런 행동도 하지 않는다.
위의 예제에서와 같이 Mock의 메소드를 호출해도 실제로 코드를 실행하지 않으므로 내부의 값은 변하지 않는다.
메소드의 리턴값도 객체의 경우 null, int의 경우 0, boolean의 경우 false로 그 타입의 기본값을 넘겨준다.
 */