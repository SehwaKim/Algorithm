package kakao.stage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz05 {
    public static void main(String[] args) {

        // 1. 문자열 2개 입력 받음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = "";
        String str2 = "";
        try {
            str1 = br.readLine();
            str2 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 2. 대문자로 변환
        char[] char1 = str1.toUpperCase().toCharArray();
        char[] char2 = str2.toUpperCase().toCharArray();


        // 3. 영문자 이외 문자 제거 후 List 에 담음
        List<Character> charTrim1 = new ArrayList<>();
        List<Character> charTrim2 = new ArrayList<>();

        for(int i=0;i<char1.length;i++){
            if(char1[i]>=65 && char1[i]<=90){
                charTrim1.add(char1[i]);
            }
        }
        for(int i=0;i<char2.length;i++){
            if(char2[i]>=65 && char2[i]<=90){
                charTrim2.add(char2[i]);
            }
        }


        // 4. 두 글자씩 묶어 String[] 에 담음
        String[] strArr1 = new String[charTrim1.size()-1];
        String[] strArr2 = new String[charTrim2.size()-1];
        for(int i=0;i<strArr1.length;i++){
            strArr1[i] = charTrim1.get(i).toString() + charTrim1.get(i+1).toString();
            System.out.print(strArr1[i]+" ");
        }
        System.out.println();
        for(int i=0;i<strArr2.length;i++){
            strArr2[i] = charTrim2.get(i).toString() + charTrim2.get(i+1).toString();
            System.out.print(strArr2[i]+" ");
        }

        // 5. 교집합 구하기
        List<Character> intersection = new ArrayList<>();






        /*double interSize = intersection.size();
        double unionSize = interSize + (set1.size() - interSize) + (set2Bak.size() - interSize);

        int jakad = (int)((interSize/unionSize)*65536);

        System.out.println("str1         str2            answer");
        System.out.println(str1+"          "+str2+"          "+jakad);*/
    }
}
