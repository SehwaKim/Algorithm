package algostudy;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FirstWeek {
    public static void main(String[] args) throws Exception {
        // 두 문자열을 사전순으로 비교하는 함수 작성하기

        // 입력 - 한 줄에 하나씩 공백없이 영어 소문자로 된 문자열 2개 주어짐. 문자열의 길이는 10만을 넘지 않음.
        // 출력 - -1, 0, 1

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int limit = Math.min(s1.length(), s2.length()); // 더 짧은 문자열을 기준으로
        int i = 0;
        while (i < limit) {
            if (s1.charAt(i) != s2.charAt(i)) {
                System.out.println(s1.charAt(i) < s2.charAt(i) ? -1 : 1);
                return;
            }
            i++;
        }
        // 모두 통과하면
        // 여기서부터는 단순 문자길이 비교
//        System.out.println(s1.length() == s2.length() ? 0 : (s1.length() < s2.length() ? -1 : 1));
        System.out.println(Integer.compare(s1.length(), s2.length()));

        // Integer 의 compareTo 내부에서 호출하는 compare (compareTo, compare 결과 동일)
        //     public static int compare(int x, int y) {
        //        return (x < y) ? -1 : ((x == y) ? 0 : 1);
        //    }
        int[] a = {1, 2, 3};
        a.clone();
    }
}
