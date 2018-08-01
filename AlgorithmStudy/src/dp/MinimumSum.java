package dp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MinimumSum {
    static int[][] dp;
    static int[][] map;

/* test case
4 4
6 7 12 5
5 3 11 18
7 17 3 3
8 10 14 9
*/
    public static void main(String[] args) throws IOException {
        // 행렬의 최단경로
        // 크기 MxN 행렬에서 1,1에서 출발해서 M,N까지 가는 경로에 적힌 숫자들의 최소합 구하기
        // 위에서 아래로 / 왼쪽에서 오른쪽으로
        // 순환식: L(m,n) = min(L(m-1,n),L(m,n-1))+m[m][j]
        // base case: 1,1일때는 m[1][1]
        // m=1 일때 L(m,n)=L(m,n-1)+m[m][n]
        // n=1 일때 L(m,n)=L(m-1,n)+m[m][n]
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] result = br.readLine().split("\\s");
        int m = Integer.parseInt(result[0]);
        int n = Integer.parseInt(result[1]);
        map = new int[m + 1][n + 1];
        dp = new int[m + 1][n + 1];
        
        for (int i = 1; i < m + 1; i++) {
            result = br.readLine().split("\\s");
            for (int j = 1; j < n + 1; j++) {
                map[i][j] = Integer.parseInt(result[j-1]);
            }
        }
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

        // top down
        System.out.println(minSumTopDown(m, n)); // 1,1부터 m,n까지 top down
        // bottom up
        System.out.println(minSumBottomUp(m, n));
    }

    private static int minSumTopDown(int m, int n) {
        if (dp[m][n] != 0) {
            return dp[m][n];
        }
        if (m == 1 && n == 1) {
            dp[m][n] = map[m][n];
        }else if (m == 1) {
            dp[m][n] = minSumTopDown(m, n - 1) + map[m][n];
        }else if (n == 1) {
            dp[m][n] = minSumTopDown(m - 1, n) + map[m][n];
        }else {
            dp[m][n] = Math.min(minSumTopDown(m - 1, n), minSumTopDown(m, n - 1)) + map[m][n];
        }
        return dp[m][n];
    }

    private static int minSumBottomUp(int m, int n) {
        // bottom up 은 그림을 그려봐라
        // 점화식 오른쪽부터 구해서 최종 답을 구해나간다
        // 1,1 부터 행순서로 차례대로 채우면 됨
        // base case 세팅
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    dp[i][j] = map[i][j];
                    continue;
                }
                if (i == 1) {
                    dp[i][j] = dp[i][j-1] + map[i][j];
                    continue;
                }
                if (j == 1) {
                    dp[i][j] = dp[i-1][j] + map[i][j];
                    continue;
                }
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + map[i][j];
            }
        }
        return dp[m][n];
    }
}
