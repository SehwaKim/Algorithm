package tdd.examples;

import java.lang.reflect.Method;

public class Exam03 {
    public static void main(String[] args) throws Exception {
        String className = "tdd.examples.MyUtil";
        String methodName = "setName";
        Object[] parameterValues = {}; //??
        Class<?>[] parameterTypes = null; //파라미터 타입들 (정의 순서대로 저장)
        Method method = null;

        Class clazz = Class.forName(className);
        Method[] methods = clazz.getDeclaredMethods();
        for(Method m : methods){
            if(methodName.equals(m.getName())){
                parameterTypes = m.getParameterTypes();
                if(parameterTypes != null){
                    method = m;
                    break;
                }
            }
        }
        if(parameterTypes != null) {
            for (int i = 0; i < parameterTypes.length; i++) {
                String classType = parameterTypes[i].getName();
                System.out.println(classType);
            }
        }

        if(method != null){
            /*Object returnValue = method.invoke(clazz.newInstance(), null);
            if(returnValue != null){
                System.out.println(returnValue.getClass().getName());
            }*/
        }
    }
}
