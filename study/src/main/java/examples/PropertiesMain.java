package examples;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;

public class PropertiesMain {
    public static void main(String[] args) {
        //classpath에서 servlet.properties를 읽어들여 그안에 key value구하기

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();//현 스레드에서 클래스로더를 얻는다
        classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource("servlet.properties");

        String filePath = url.getPath();
        String value = "";

        try {
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream(filePath);
            properties.load(new BufferedInputStream(fis));
            for(String name : properties.stringPropertyNames()){
                value = properties.getProperty(name).trim();
                System.out.println(name+"="+value);
            }
            fis.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
