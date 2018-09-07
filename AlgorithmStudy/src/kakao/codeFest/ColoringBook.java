package kakao.codeFest;

import java.util.*;

public class ColoringBook {
    // 카카오프렌즈 컬러링북
    public static void main(String[] args) {
        int m = 6;
        int n = 4;
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
//        int m = 1;
//        int n = 1;
//        int[][] picture = {{3}};

        int[] answer = new ColoringBook().solution(m, n, picture);
        System.out.println(Arrays.toString(answer));
    }

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] == 0) {
                    continue;
                }
                int temp = dfs(picture[i][j], i, j, picture);
                maxSizeOfOneArea = (temp > maxSizeOfOneArea) ? temp : maxSizeOfOneArea;
                numberOfArea++;

            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    private int dfs(int color, int y, int x, int[][] picture) {
        picture[y][x] = 0;
        int[] dy = {1, -1, 0, 0};
        int[] dx = {0, 0, 1, -1};

        int result = 1;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || ny >= picture.length || nx >= picture[ny].length) {
                continue;
            }
            if (picture[ny][nx] != color) {
                continue;
            }
            result += dfs(color, ny, nx, picture);
        }
        return result;
    }
}
