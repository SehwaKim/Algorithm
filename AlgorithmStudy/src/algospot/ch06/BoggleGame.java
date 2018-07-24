package algospot.ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BoggleGame {
    static char[][] board = new char[5][5];
    static List<Case> cases = new ArrayList<>();
    static int[] dy = {1, -1, 0, 0, -1, 1, 1, -1};
    static int[] dx = {0, 0, 1, -1, -1, 1, -1, 1};

    public static void main(String[] args) {
        init();
        cases.stream().forEach(c -> {
            if (hasWord(c.y, c.x, c.word)) {
                System.out.println(c.word + "존재");
            } else {
                System.out.println(c.word + "존재하지 않음");
            }
        });
    }

    private static boolean hasWord(int y, int x, String word) {
        if (!inRange(y, x)) {
            return false;
        }

        if (board[y][x] != word.charAt(0)) {
            return false;
        }

        if (word.length() == 1) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            hasWord(ny, nx, word.substring(1));
        }

        return true;
    }

    private static boolean inRange(int y, int x) {
        if (y < 0 || y >= board.length || x < 0 || x >= board.length) {
            return false;
        }
        return true;
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int testCase = parseInt(br.readLine());
            for (int i = 0; i < 5; i++) {
                board[i] = br.readLine().toCharArray();
            }
            for (int i = 0; i < testCase; i++) {
                String[] token = br.readLine().split("\\s");
                int y = parseInt(token[0]);
                int x = parseInt(token[1]);
                cases.add(new Case(y, x, br.readLine()));
            }

        } catch (IOException e) {}
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }

    private static class Case {
        int y;
        int x;
        String word;

        public Case(int y, int x, String word) {
            this.y = y;
            this.x = x;
            this.word = word;
        }
    }
}
/*
3
URLPM
XPRET
GIAET
XTNZY
XOQRS
1 1
PRETTY
2 0
GIRL
1 2
REPEAT
*/