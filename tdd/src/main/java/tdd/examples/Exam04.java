package tdd.examples;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class Exam04 {
    public static void main(String[] args) throws Exception {
        //mytest 애노테이션 붙은 메소드이름만 출력
        String className = "tdd.examples.MyUtil";
        Class clazz = Class.forName(className);
        Method[] methods = clazz.getDeclaredMethods();

        for(Method method : methods){
            /*Annotation[] an = method.getDeclaredAnnotations();
            for(Annotation a : an){
                if(a instanceof MyTest){
                    System.out.println(method.getName());
                }
            }*/
            MyTest annotation = method.getAnnotation(MyTest.class);
            if(annotation != null){
                method.invoke(clazz.newInstance());
            }
        }
    }
}
