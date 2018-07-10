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
        setData();
        bfsAll();

        // bfs 가 전혀 호출되지 않고 0이 없으면 0 출력
        // 0이 있으면 -1 출력
        if(level > 1){
            if (isAnyZero()) {
                System.out.println("-1");
            } else {
                System.out.println(level-1);
            }
        }else {
            if (isAnyZero()) {
                System.out.println("-1");
            } else {
                System.out.println("0");
            }
        }
    }

    private static boolean isAnyZero() {
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j].equals("0")){
                    return true;
                }
            }
        }
        return false;
    }

    private static void setData() {
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
        if(vertex[1] - 1 >= 0){ // 동
            if(map[vertex[0]][vertex[1] - 1].equals("0")){
                queue.offer(new Integer[]{vertex[0], vertex[1] - 1});
                map[vertex[0]][vertex[1] - 1] = "2";
            }
        }
        if(vertex[1] + 1 < m){ // 서
            if(map[vertex[0]][vertex[1] + 1].equals("0")){
                queue.offer(new Integer[]{vertex[0], vertex[1] + 1});
                map[vertex[0]][vertex[1] + 1] = "2";
            }
        }
        if(vertex[0] + 1 < n){ // 남
            if(map[vertex[0] + 1][vertex[1]].equals("0")){
                queue.offer(new Integer[]{vertex[0] + 1, vertex[1]});
                map[vertex[0] + 1][vertex[1]] = "2";
            }
        }
        if(vertex[0] - 1 >= 0){ // 북
            if(map[vertex[0] - 1][vertex[1]].equals("0")){
                queue.offer(new Integer[]{vertex[0] - 1, vertex[1]});
                map[vertex[0] - 1][vertex[1]] = "2";
            }
        }
    }
}
