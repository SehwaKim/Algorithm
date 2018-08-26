package kakao.stage1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
5
[9, 20, 28, 18, 11]
[30, 1, 21, 17, 28]


6
[46, 33, 33, 22, 31, 50]
[27, 56, 19, 14, 14, 10]
* */
public class Ex01 {
    // 비밀지도 난이도 하
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine());

        String str1 = br.readLine();
        String str2 = br.readLine();
        String[] arr1 = str1.substring(1, str1.length() - 1).split(", ");
        String[] arr2 = str2.substring(1, str2.length() - 1).split(", ");
        System.out.println(str1);
        System.out.println(str2);

        for (int i = 0; i < n; i++) {
            arr1[i] = Integer.toBinaryString(parseInt(arr1[i]));
        }

        for (int i = 0; i < n; i++) {
            arr2[i] = Integer.toBinaryString(parseInt(arr2[i]));
        }

        String[] resultArr = new String[n];

        for (int i = 0; i < n; i++) {
            String result = "";
            String a = arr1[i];
            String b = arr2[i];
            int sizeOfA = a.length();
            int sizeOfB = b.length();
            // 자리수 맞춰주기
            if (sizeOfA < n) {
                for (int j = 0; j < n - sizeOfA; j++) {
                    a = "0" + a;
                }
            }
            if (sizeOfB < n) {
                for (int j = 0; j < n - sizeOfB; j++) { // b의 길이가 자꾸 변하니까.
                    b = "0" + b;
                }
            }

            // 비트단위로 OR 연산
            for (int j = 0; j < n; j++) {
                int num1 = a.charAt(j) - 48;
                int num2 = b.charAt(j) - 48;
                if ((num1 | num2) == 1) {
                    result += "#";
                } else {
                    result += " ";
                }
            }

            resultArr[i] = result;
        }

        System.out.println(Arrays.toString(resultArr));
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

}