package boj1987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    static char[][] map;
    static int max = 1;
    static int[] dy = {1, 0, -1, 0};
    static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) {
        init();
        dfsAll();

        System.out.println(max);
    }

    private static void dfsAll() {
        Set<Character> visited = new HashSet<>();
        dfs(new int[]{0, 0}, 1, visited);
    }

    private static void dfs(int[] vertex, int level, Set<Character> visited) {
        int y = vertex[0];
        int x = vertex[1];
        visited.add(map[y][x]);

        boolean searchable = false;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny >= map.length || nx >= map[y].length || ny < 0 || nx < 0) {
                continue;
            }

            if (visited.contains(map[ny][nx])) {
                continue;
            }

            searchable = true;
            dfs(new int[]{ny, nx}, level + 1, visited);
        }

        if (!searchable) {
            if(max < level) {
                max = level;
            }
        }
        visited.remove(map[y][x]);
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] arr = (br.readLine()).split(" ");
            int r = Integer.parseInt(arr[0]);
            int c = Integer.parseInt(arr[1]);
            map = new char[r][c];

            for (int i = 0; i < r; i++) {
                map[i] = br.readLine().toCharArray();
            }

        } catch (IOException e) {}
    }
}
