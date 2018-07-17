package boj13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;
    static int m;
    static char[][] board;
    static Queue<State> queue = new LinkedList<>();
    static int[] dy = { 1, -1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };
    static boolean reached;
    static boolean failed;
    static int totDepth;

    public static void main(String[] args) {
        init();
        bfsAll();
        print(result());
    }

    private static int result() {
        if (failed) {
            return -1;
        }
        return totDepth;
    }

    private static void print(int result) {
        System.out.println(result);
    }

    private static void bfsAll() {
        while (!queue.isEmpty()) {
            if (reached || failed) {
                return;
            }

            State vertex = queue.poll();
            bfs(vertex);
        }
    }

    private static void bfs(State vertex) {
        int y = vertex.ry;
        int x = vertex.rx;
        int by = vertex.by;
        int bx = vertex.bx;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || ny >= n || nx < 0 || nx >= m) {
                continue;
            }

            if (board[ny][nx] == '#') {
                continue;
            }

            for (int j = 0; j < 4; j++) {
                int nby = by + dy[j];
                int nbx = bx + dx[j];
                if (nby < 0 || nby >= n || nbx < 0 || nbx >= m) {
                    continue;
                }

                if (nby == ny && nbx == nx) {
                    continue;
                }

                if (board[nby][nbx] != '.') {
                    continue;
                }

                if (board[ny][nx] == 'O') {
                    reached = true;
                    totDepth = vertex.depth + 1;
                    return;
                }

                queue.offer(new State(ny, nx, nby, nbx, vertex.depth + 1));
            }
        }

        if (!reached && vertex.depth == 10) {
            failed = true;
            return;
        }
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] token = br.readLine().split("\\s");
            n = parseInt(token[0]);
            m = parseInt(token[1]);
            board = new char[n][m];
            int[] r_pos = new int[2];
            int[] b_pos = new int[2];

            br.readLine();
            for (int i = 0; i < n - 2; i++) {
                String line = br.readLine().substring(1, m - 1);
                board[i] = line.toCharArray();
                for (int j = 0; j < m - 2; j++) {
                    if (board[i][j] == 'R') {
                        r_pos[0] = i;
                        r_pos[1] = j;
                    }
                    if (board[i][j] == 'B') {
                        b_pos[0] = i;
                        b_pos[1] = j;
                    }
                }
            }
            br.readLine();
            n -= 2;
            m -= 2;
            queue.offer(new State(r_pos[0], r_pos[1], b_pos[0], b_pos[1], 0));

        } catch (IOException e) {}
    }

    private static int parseInt(String value) {
        return Integer.parseInt(value);
    }

    private static class State {
        private final int ry;
        private final int rx;
        private final int by;
        private final int bx;
        private final int depth;

        public State(int ry, int rx, int by, int bx, int depth) {
            this.ry = ry;
            this.rx = rx;
            this.by = by;
            this.bx = bx;
            this.depth = depth;
        }
    }
}
