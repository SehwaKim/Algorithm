package algorithm.practice.boj9095;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());
        int[] cases = new int[c];
        for (int i = 0; i < c; i++) {
            cases[i] = Integer.parseInt(br.readLine());
        }

        // counting 에 해당하는 문제 (경우의 수)
        // 점화식을 세우기위해 n=1, n=2 ... 관찰하고
        // 각 항 사이의 관계를 관찰해서 식으로 만들어야함
        // dp[n]은 n을 1,2,3의 합으로 나타내는 방법의 수 --> n을 만드는데 필요했던 조합의 수!
        // dp[n] = dp[n-1] + dp[n-2] + dp[n-3]

        // bottom up
        for (int i = 0; i < c; i++) {
            int n = cases[i];
            int[] dp = new int[11]; // n은 최대 10
            System.out.println(countBottomUp(n, dp));
        }

        // top down
        for (int i = 0; i < c; i++) {
            int n = cases[i];
            int[] dp = new int[11]; // n은 최대 10
            System.out.println(countTopDown(n, dp));
        }
    }

    private static int countBottomUp(int n, int[] dp) {
        dp[0] = dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }

    private static int countTopDown(int n, int[] dp) {
        if (n == 0 || n == 1) {
            dp[n] = 1;
            return dp[n];
        }
        if (n == 2) {
            dp[n] = 2;
            return dp[n];
        }
        if (dp[n] == 0) {
            dp[n] = countTopDown(n - 1, dp) + countTopDown(n - 2, dp) + countTopDown(n - 3, dp);
        }
        return dp[n];
    }
}