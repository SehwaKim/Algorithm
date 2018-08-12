package boj10844;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    // "cache 를 이차원 배열에 할 수 있는가?"
    // 이차원배열에 캐싱을 해야겠다는 발상을 한번에 떠올리기란 쉽지 않다
    // 어쨌든 DP를 풀려면 점화식을 세워야 하니까
    // 점화식을 도출하는 과정에서 어떤 정보를 어떻게 저장해야할지 각이 나오면
    // 자연스럽게 1차원 배열이든 2차원 배열이든 어디에 저장해야겠구나라는 발상이 가능하겠지
    // 점화식을 쉽게 도출하는 방법은 N=1, N=2 케이스를 직접 적어보는 거임

    static long[][] cache = new long[101][10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(numOfCaseNumbers(n));
    }

    private static long numOfCaseNumbers(int n) {
        // base case: N=1일때
        Arrays.fill(cache[1], 1);
        cache[1][0] = 0; // 0으로 시작하는 계단수는 없음

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= 8; j++) {
                cache[i][j] = (cache[i - 1][j - 1] + cache[i - 1][j + 1]) % 1000000000; // 왜?
            }
            cache[i][0] = cache[i - 1][1];
            cache[i][9] = cache[i - 1][8];
        }

        long sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += cache[n][i];
        }
        return sum % 1000000000;
    }
}