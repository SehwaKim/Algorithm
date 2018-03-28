package tdd.examples.mockito;

import java.util.List;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

// Mocking과 Verification
public class Examples01 {
    public static void main(String[] args){
        //의존성 있는 객체를 목으로 만들어 쓰겠다 - 쓰는 이유


        // mock
        List mockedList = mock(List.class);
        //리스트를 구현한 객체가 실행시에 동적으로 만들어진다 - 목객체
        //List를 구현한 객체를 mockito가 만드는 것이다. 그 객체는 실제 모듈과 비슷해 보이는 가짜 객체.
        System.out.println(mockedList.getClass().getName());

        // mock 사용하기
        mockedList.add("one");
        mockedList.clear(); //메소드가 동작은 하지만!! 진짜 실제로 기능하지는 않는다. 진짜 똑같이 실제로 구현한건 아니다
        //이렇게 메소드를 사용하면 사용했었다는 걸 목객체는 안다 -> verify로 검증할 수 있다.

        // verification
        verify(mockedList).add("three"); //목객체의 이 메소드가 성공적으로 사용됬는지 확인해주는 메소드
        verify(mockedList).clear(); //verify는 한마디로 기록된적 있는지 알아보는 것
        //흐름상 어떤 메소드가 호출되어야하는데 호출된적 없다면... 로직이 잘못된 것
        //적절한 파라미터까지 넣어져서 제대로 호출됬는지 테스트할 수 있다

        //에러메세지
        //Exception in thread "main" Argument(s) are different! Wanted: list.add("two");
        //예상과 다른 파라미터값 넘기면 안되는 경우도 있으니까
    }
}
