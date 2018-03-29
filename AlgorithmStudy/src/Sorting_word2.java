import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Sorting_word2 {
    public static void main(String[] args) {
        //단어 정렬 문제 다른 풀이 (중복 제거 위해 HashSet 이용)

        Scanner sc = new Scanner(System.in);
        String n = sc.next();
        int num = Integer.parseInt(n);

        String word;
        HashSet<String> hs = new HashSet<String>();

        for(int i=0;i<num;i++){
            hs.add(sc.next());
        }

        //HashSet을 array로
        Object[] str = hs.toArray();
        Arrays.sort(str);

        Object tmp;
        for(int i=0;i<str.length-1;i++){
            for(int j=0;j<str.length-1-i;j++){

                if(((String)str[j]).length()>((String)str[j+1]).length()){
                    tmp = str[j+1];
                    str[j+1] = str[j];
                    str[j] = tmp;
                }
            }
        }

        for(Object e : str){
            System.out.println(e);
        }
    }
}
