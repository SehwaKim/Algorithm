package tdd.examples.mockito;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
//모키토에서 제공하는 MockitoJUnitRunner와 함께 실행이 된다. 확장 포인트.
@RunWith(MockitoJUnitRunner.class) //이걸써주면 @before방법안써도 자동으로 다 @mock이라고 써있는 곳에 mock객체 다 넣어줌;;;;
public class Examples08 {
    @Mock
    private List mockedList;

    @Test
    public void test() {
        // test here
    }
}
