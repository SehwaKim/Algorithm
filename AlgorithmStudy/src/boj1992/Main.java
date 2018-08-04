package boj1992;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().trim().toCharArray();
        }
        
        System.out.println(compress(n, 0, 0));
    }

    // n 은 정사각형의 길이
    private static String compress(int n, int startY, int startX) {
        String result = "(";
        char first = map[startY][startX];
        boolean isUniform = true;
        loop: for (int i = startY; i < startY + n; i++) {
            for (int j = startX; j < startX + n; j++) {
                if (first != map[i][j]) {
                    isUniform = false;
                    break loop;
                }
            }
        }
        if (isUniform) {
            return first + "";
        } else {
            result += compress(n / 2, startY, startX);
            result += compress(n / 2, startY, startX + n / 2);
            result += compress(n / 2, startY + n / 2, startX);
            result += compress(n / 2, startY + n / 2, startX + n / 2);
            result += ")";
        }

        return result;
    }
}
