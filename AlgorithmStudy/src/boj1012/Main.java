package boj1012;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<boolean[][]> maps;
    static int[] results;
    static int dx[] = { 1, 0, -1, 0 };
    static int dy[] = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        init();
        searchByCase();
        print(results);
    }

    private static void print(int[] results) {
        for (int result : results) {
            System.out.println(result);
        }
    }

    private static void searchByCase() {
        for (int i = 0; i < maps.size(); i++) {
            results[i] = dfsAll(maps.get(i));
        }
    }

    private static int dfsAll(boolean[][] map) {
        int numOfComponent = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]) {
                    map[i][j] = false;
                    dfs(new int[]{i,j}, map);
                    numOfComponent++;
                }
            }
        }
        return numOfComponent;
    }

    private static void dfs(int[] vertex, boolean[][] map) {
        int y = vertex[0];
        int x = vertex[1];

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= map.length || nx >= map[y].length || nx < 0) {
                continue;
            }
            if(!map[ny][nx]) {
                continue;
            }
            map[ny][nx] = false;
            dfs(new int[]{ny, nx}, map);
        }
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int testCase = Integer.parseInt(br.readLine());
            maps = new ArrayList<>(testCase);
            results = new int[testCase];
            for (int i = 0; i < testCase; i++) {
                String[] arr = br.readLine().split(" ");
                int m = Integer.parseInt(arr[0]);
                int n = Integer.parseInt(arr[1]);
                boolean[][] map = new boolean[n][m];
                maps.add(map);
                int numOfCabbage = Integer.parseInt(arr[2]);
                for (int j = 0; j < numOfCabbage; j++) {
                    arr = br.readLine().split(" ");
                    int x = Integer.parseInt(arr[0]);
                    int y = Integer.parseInt(arr[1]);
                    map[y][x] = true;
                }
            }

        } catch (IOException e) {}
    }
}
