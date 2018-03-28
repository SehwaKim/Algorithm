package tdd.examples;

import java.lang.reflect.Method;

public class Exam02 {
    public static void main(String[] args) throws Exception {
        String className = "tdd.examples.MyUtil";
        String methodName = "print1";

        Class clazz = Class.forName(className);
        Method method = clazz.getMethod(methodName, null);
        if(method != null){
            Object returnValue = method.invoke(clazz.newInstance(), null);
            if(returnValue != null){
                System.out.println(returnValue.getClass().getName());
            }
        }
    }
}