package boj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int m;
    static String[][] map;

    // 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸

    public static void main(String[] args) {
        solve();
        bfsAll();
    }

    private static void solve() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            String[] arr = line.split(" ");
            m = Integer.parseInt(arr[0]);
            n = Integer.parseInt(arr[1]);
            map = new String[n][];

            for(int i=0; i<n; i++){
                line = br.readLine();
                arr =line.split(" ");
                map[i] = arr;
            }
        }catch (IOException e){}
    }

    private static void bfsAll() {

    }
}
