package boj2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] steps;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine().trim());
        steps = new int[n + 1];
        dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            steps[i] = parseInt(br.readLine().trim());
        }

        // dp[n]은 바로 전 계단을 밟은 경우와 밟지 않은 경우로 나눌 수 있다.
        // dp[n] = step[n] + step[n-1] + dp[n-3] ----> 바로 전 계단을 밟으면 전전 계단은 못 밞음
        // dp[n] = step[n] + dp[n-2] ----> 전전 계단을 밟는 경우
        // base case: dp[3] = step[3] + step[2] + dp[0]
        //            dp[3] = step[3] + dp[1]
        //            dp[2] = step[2] + step[1] + dp[-1]?? dp[2]부터 정의되어야 함

        System.out.println(getSumBottomUp(n));
    }

    private static int getSumBottomUp(int n) {
        dp[1] = steps[1];
        dp[2] = steps[1] + steps[2];
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(steps[i - 1] + dp[i - 3], dp[i - 2]) + steps[i];
        }
        return dp[n];
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
