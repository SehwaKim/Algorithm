package boj2468;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static Set<Integer> heights;
    static List<Integer> result;

    public static void main(String[] args) {
        init();
        searchSafetyZone();
        print(maxSafetyZone());
    }

    private static void print(int maxSafetyZone) {
        System.out.println(maxSafetyZone);
    }

    private static int maxSafetyZone() {
        Collections.sort(result);
/*
        3
        1 1 1
        1 1 1
        1 1 1
*/
        // 위와 같은 경우를 고려해야 함
        if (heights.size() == 0) {
            return 1;
        }

        return result.get(result.size() - 1);
    }

    private static void searchSafetyZone() {
        result = new ArrayList<>(heights.size());

        for (Integer height : heights) {
            int numOfComponent = dfsAll(height);
            result.add(numOfComponent);
        }
    }

    private static int dfsAll(Integer height) {
        boolean[][] visited = new boolean[map.length][map.length];
        int numOfComponent = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] > height) {
                    if (!visited[i][j]) {
                        visited[i][j] = true;
                        dfs(new int[]{i,j}, height, visited);
                        numOfComponent++;
                    }
                }
            }
        }
        return numOfComponent;
    }

    private static void dfs(int[] vertex, int height, boolean[][] visited) {
        int y = vertex[0];
        int x = vertex[1];

        if (y - 1 >= 0) {
            if (map[y - 1][x] > height) {
                if (!visited[y - 1][x]) {
                    visited[y - 1][x] = true;
                    dfs(new int[]{y - 1, x}, height, visited);
                }
            }
        }
        if (y + 1 < map.length) {
            if (map[y + 1][x] > height) {
                if (!visited[y + 1][x]) {
                    visited[y + 1][x] = true;
                    dfs(new int[]{y + 1, x}, height,  visited);
                }
            }
        }
        if (x - 1 >= 0) {
            if (map[y][x - 1] > height) {
                if (!visited[y][x - 1]) {
                    visited[y][x - 1] = true;
                    dfs(new int[]{y, x - 1}, height,  visited);
                }
            }
        }
        if (x + 1 < map.length) {
            if (map[y][x + 1] > height) {
                if (!visited[y][x + 1]) {
                    visited[y][x + 1] = true;
                    dfs(new int[]{y, x + 1}, height,  visited);
                }
            }
        }
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            n = Integer.parseInt(line); // 2 <= n <= 100
            map = new int[n][n];
            heights = new HashSet<>();

            int max = 1;

            for (int i = 0; i < n; i++) { // 1 <= vertex <= 100
                line = br.readLine();
                String[] arr = line.split(" ");
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(arr[j]);
                    if (map[i][j] > max) { max = map[i][j]; }
                    heights.add(map[i][j]);
                }
            }

            heights.remove(max);

        } catch (IOException e) {}
    }
}
