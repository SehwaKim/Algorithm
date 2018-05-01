package kakao.stage1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Quiz05 {
    public static void main(String[] args) {
        // 1. 두 문자열 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = "";
        String str2 = "";
        try {
            str1 = br.readLine();
            str2 = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 2. 두글자씩 쪼개서 대문자로 변환한 다음 String[]에 담는다
        String[] strArr1 = new String[str1.length()-1];
        String[] strArr2 = new String[str2.length()-1];
        for(int i=0;i<strArr1.length;i++){
            strArr1[i] = str1.substring(i,i+2).toUpperCase();
            System.out.print(strArr1[i]+" ");
        }
        System.out.println();
        for(int i=0;i<strArr2.length;i++){
            strArr2[i] = str2.substring(i,i+2).toUpperCase();
            System.out.print(strArr2[i]+" ");
        }

        // 3. 영문자 이외의 문자가 포함된 문자쌍 빼고 List 에 담기
        List<String> strlist1 = new ArrayList<>();
        List<String> strlist2 = new ArrayList<>();
        for(String s : strArr1){
            if(s.charAt(0)>='A' && s.charAt(0)<='Z' && s.charAt(1)>='A' && s.charAt(1)<='Z'){
                strlist1.add(s);
            }
        }
        for(String s : strArr2){
            if(s.charAt(0)>='A' && s.charAt(0)<='Z' && s.charAt(1)>='A' && s.charAt(1)<='Z'){
                strlist2.add(s);
            }
        }

        // 4. 교집합 구하기
        List<String> intersection = new ArrayList<>();
        List<Integer> checkedindex = new ArrayList<>();
        for(int i=0;i<strlist1.size();i++){
            for(int j=0;j<strlist2.size();j++){
                if(strlist1.get(i).compareTo(strlist2.get(j)) == 0 && !checkedindex.contains(j)){
                    intersection.add(strlist1.get(i));
                    checkedindex.add(j);
                    break;
                }
            }
        }

        // 5. 자카드 유사도 계산
        int interSize = intersection.size();
        int unionSize = strlist1.size() + strlist2.size() - interSize;
        int jaccard;

        if(interSize == 0 && unionSize == 0){
            jaccard = 1;
        }else {
            jaccard = (int) ((interSize/(unionSize*1.0)) * 65536);
        }

        System.out.println("\n--------------------------");
        System.out.println("교집합 크기 : "+interSize);
        System.out.println("합집합 크기 : "+unionSize);
        System.out.println("--------------------------");
        System.out.println("str1 : "+str1);
        System.out.println("str2 : "+str2);
        System.out.println("answer : "+jaccard);
    }
}
