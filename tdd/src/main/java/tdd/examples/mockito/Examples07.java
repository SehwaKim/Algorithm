package tdd.examples.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;


public class Examples07 {
    @Mock private List mockedList;
    private List mockedList2;
    private List mockedList3;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this); // mock all the field having @Mock annotation
        mockedList2 = Mockito.mock(List.class);
        mockedList3 = Mockito.mock(List.class);
    }

    @Test
    public void test() {
        // test here
    }
}
