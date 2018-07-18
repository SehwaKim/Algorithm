package boj13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char[][] map;
    static Node hole;
    static Queue<State> queue = new LinkedList<>();
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static Map<Node, List<Node>> discovered;
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
        return totDepth > 0 ? totDepth : -1;
    }

    private static void print(int result) {
        System.out.println(result);
    }

    private static void bfsAll() {
        while (!queue.isEmpty()) {
            if (reached) {
                return;
            }

            State vertex = queue.poll();

            if (vertex.depth > 10) {
                failed = true;
                return;
            }

            bfs(vertex);
        }
    }

    private static void bfs(State vertex) {
        int y = vertex.red.row;
        int x = vertex.red.col;
        int by = vertex.blue.row;
        int bx = vertex.blue.col;

        for (int i = 0; i < 4; i++) {
            int ny = y;
            int nx = x;
            int nby = by;
            int nbx = bx;
            boolean blockedByR = false;
            boolean blockedByB = false;
            boolean redInHole = false;
            boolean blueInHole = false;

            while (map[nby + dy[i]][nbx + dx[i]] != '#') { // 블루볼 이동 시키기
                if (nby + dy[i] == y && nbx + dx[i] == x) { // 가려는 곳에 레드볼이 있다
                    blockedByR = true;
                    break;
                }
                if (map[nby + dy[i]][nbx + dx[i]] == 'O') {
                    blueInHole = true;
                    break;
                }
                nby += dy[i];
                nbx += dx[i];
            }

            if (blueInHole) {
                continue;
            }

            while (map[ny + dy[i]][nx + dx[i]] != '#') { // 레드볼 이동 시키기
                if (ny + dy[i] == by && nx + dx[i] == bx) { // 가려는 곳에 블볼이 있다
                    blockedByB = true;
                    break;
                }
                if (map[ny + dy[i]][nx + dx[i]] == 'O') {
                    redInHole = true;
                }

                ny += dy[i];
                nx += dx[i];
            }

            if (blockedByB) {
                ny = nby - dy[i];
                nx = nbx - dx[i];
            } else if (blockedByR) {
                nby = ny - dy[i];
                nbx = nx - dx[i];
            }

            if (redInHole) {
                if (blockedByR) {
                    continue;
                }
                reached = true;
                totDepth = vertex.depth + 1;
                return;
            }

            Node newRed = new Node(ny, nx);
            Node newBlue = new Node(nby, nbx);

            if (discovered.containsKey(newRed)) {
                if (discovered.get(newRed).contains(newBlue)) {
                    continue;
                }
            } else {
                discovered.put(newRed, new LinkedList<>());
            }

            discovered.get(newRed).add(newBlue);
            queue.offer(new State(newRed, newBlue, vertex.depth + 1));
        }
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] token = br.readLine().split("\\s");
            int n = parseInt(token[0]);
            int m = parseInt(token[1]);
            map = new char[n][m];
            Node red = null;
            Node blue = null;
            discovered = new HashMap<>();

            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 'R') {
                        red = new Node(i, j);
                    }
                    if (map[i][j] == 'B') {
                        blue = new Node(i, j);
                    }
                    if (map[i][j] == 'O') {
                        hole = new Node(i, j);
                    }
                }
            }

            discovered.put(red, new LinkedList<>());
            discovered.get(red).add(blue);
            queue.offer(new State(red, blue, 0));

        } catch (IOException e) {}
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static class State {
        private final Node red;
        private final Node blue;
        private final int depth;

        public State(Node red, Node blue, int depth) {
            this.red = red;
            this.blue = blue;
            this.depth = depth;
        }
    }

    private static class Node {
        private final int row;
        private final int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            int h1 = row+"".hashCode();
            int h2 = col+"".hashCode();
            return h1 + h2;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Node other = (Node) obj;
            if (row != other.row) {
                return false;
            }
            if (col != other.col) {
                return false;
            }
            return true;
        }
    }
}
