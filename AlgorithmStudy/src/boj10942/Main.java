package boj10942;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = parseInt(br.readLine());
        String s = "a " + br.readLine();
        String[] line = s.split("\\s");
        String[] numbers = line;
        int M = parseInt(br.readLine());
        String[][] questions = new String[M][];
        for (int i = 0; i < M; i++) {
            line = br.readLine().split("\\s");
            questions[i] = line;
        }

        // dp[S][E] = S와 E가 같고 그 사이 수가 "펠린드롬인지 아닌지" -> 이거를 생각해내야 함
        // dp[S][E] = numbers[S] == numbers[E] && dp[S+1][E-1]
        // 그리고 S=E일 때는 당연히 펠린드롬
        // E-S=1, dp[S][E] = numbers[S] == numbers[E]

        boolean[][] dp = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i][i] = true;
            if (i == 1) {
                continue;
            }
            if (numbers[i].equals(numbers[i - 1])) {
                dp[i - 1][i] = true;
            }
        }

        for (int i = 2; i <= N - 1; i++) {
            for (int j = 1; j <= N - i; j++) {
                if (numbers[j].equals(numbers[j + i]) && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            isPalindrome(numbers, questions[i], dp);
        }

    }

    private static void isPalindrome(String[] numbers, String[] question, boolean[][] dp) {
        int start = parseInt(question[0]);
        int end = parseInt(question[1]);
        if (dp[start][end]) {
            System.out.println(1);
            return;
        }
        System.out.println(0);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
