package kakao.codeFest;

import java.util.Arrays;

public class Heaven {
    int MOD = 20170805;
    int[][][] memo;

    public static void main(String[] args) {
//        int[][] cityMap = {{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}};
//        int answer = new Heaven().solution(3, 6, cityMap);
        int[][] cityMap = {{0, 2, 0}, {0, 0, 0}, {0, 0, 0}};
        int answer = new Heaven().solution(3, 3, cityMap);
        System.out.println(answer);
    }

    public int solution(int m, int n, int[][] cityMap) {
        // 캐시 초기화
        memo = new int[m][n][1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                memo[i][j][0] = -1;
            }
        }
        int a = dfs(0, 0, 0, 0, cityMap);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i+", "+j+" "+Arrays.toString(memo[i][j]));
            }
        }

        return a;
    }

    private int dfs(int py, int px, int y, int x, int[][] cityMap) {
        int m = cityMap.length;
        int n = cityMap[0].length;

        // base case
        if (y >= m || x >= n) {
            return 0;
        }

        if (y == m - 1 && x == n - 1) {
            memo[y][x][0] = 1;
            return memo[y][x][0];
        }

        if (cityMap[y][x] == 1) {
            memo[y][x][0] = 0;
        }

        if (memo[y][x][0] == -1) {
            memo[y][x][0] = 0;
            if (cityMap[y][x] == 0 || py + 1 == y) {
                memo[y][x][0] += dfs(y, x, y + 1, x, cityMap);
            }
            if (cityMap[y][x] == 0 || px + 1 == x) {
                memo[y][x][0] += dfs(y, x, y, x + 1, cityMap);
            }
        }

        return memo[y][x][0] % MOD;
    }
}
