package dp;

public class Binomial {
    static int[][] dp = new int[11][11];

    public static void main(String[] args) {
        // 이항계수 계산하기
        // 조합이랑 같은 뜻
        // 조합의 경우
        // n개 중 r개를 중복없이 뽑는 코드를 재귀적으로 구현했었음 (모든 조합구하기 brute force)
        // 하지만 이항계수를 만족시키는 점화식(recurrence formula)을 이용해서 재귀적으로 구현할수도 있음
        System.out.println(bino(5, 4)); // 5개 중 4개 뽑기

        // 그냥 재귀호출하면 중복되는 Sub Problem 이 많이 생김 (많은 계산 중복)
        // 동적계획법으로 풀자
        // top down
        System.out.println(dpBinoTopDown(5, 4));
        // bottom up
        System.out.println(dpBinoBottomUp(5, 4));
    }

    private static int bino(int n, int k) {
        //점화식: bino(n,k)=bino(n-1,k-1)+bino(n-1,k)
        //base case: if n=k of k=0 --> bino(n,k)=1
        if (n == k || k == 0) {
            return 1;
        }
        return bino(n - 1, k - 1) + bino(n - 1, k);
    }

    private static int dpBinoTopDown(int n, int k) {
        if (n == k || k == 0) {
            return 1;
        }
        if (dp[n][k] == 0) {
            dp[n][k] = dpBinoTopDown(n - 1, k - 1) + dpBinoTopDown(n - 1, k);
        }
        return dp[n][k];
    }

    private static int dpBinoBottomUp(int n, int k) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= i && j<=k; j++) {
                if (i == j || j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[n][k];
    }
}
