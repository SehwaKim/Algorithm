package tdd.examples;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //메소드에 붙을 수 있는 애노테이션이란 뜻
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTest {
}
