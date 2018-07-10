package boj7576;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    static int n;
    static int m;
    static String[][] map;
    static Queue<Integer[]> queue = new LinkedList<>();
    static int level = 0;

    // 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸

    public static void main(String[] args) {
        init();
        bfsAll();

        // 맵에 0이 있으면 -1 출력 (모든 토마토를 익게 하지는 못하는 상태)
        // 탐색된 적 없고 맵에 0도 없으면 0 출력 (모든 토마토 익은 상태)

        if (zeroExist()) {
            System.out.println("-1");
            return;
        }

        if (neverSearched()) {
            System.out.println("0");
            return;
        }

        System.out.println(level - 1);
    }

    private static boolean neverSearched() {
        return level < 2;
    }

    private static boolean zeroExist() {
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j].equals("0")){
                    return true;
                }
            }
        }
        return false;
    }

    private static void init() {
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

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j].equals("1")){
                        queue.offer(new Integer[]{i, j});
                    }
                }
            }

        }catch (IOException e){}
    }

    private static void bfsAll() {
        // 각 익은토마토(1)를 기점으로 bfs 시작
        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            for (int i = 0; i < queueSize; i++) {
                bfs(queue.remove());
            }

            level++;
        }
    }

    private static void bfs(Integer[] vertex) {
        int y = vertex[0];
        int x = vertex[1];

        if(x - 1 >= 0){
            if(map[y][x - 1].equals("0")){
                queue.offer(new Integer[]{y, x - 1});
                map[y][x- 1] = "2";
            }
        }
        if(x + 1 < m){
            if(map[y][x + 1].equals("0")){
                queue.offer(new Integer[]{y, x + 1});
                map[y][x + 1] = "2";
            }
        }
        if(y + 1 < n){
            if(map[y + 1][x].equals("0")){
                queue.offer(new Integer[]{y + 1, x});
                map[y + 1][x] = "2";
            }
        }
        if(y - 1 >= 0){
            if(map[y - 1][x].equals("0")){
                queue.offer(new Integer[]{y - 1, x});
                map[y - 1][x] = "2";
            }
        }
    }
}
