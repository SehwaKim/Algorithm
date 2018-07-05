package boj2178;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;
    static int m;
    static String[][] map;
    static int level = 0;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            String[] arr = line.split(" ");
            n = Integer.parseInt(arr[0]);
            m = Integer.parseInt(arr[1]);
            map = new String[n][];

            for(int i=0; i<n; i++){
                line = br.readLine();
                arr =line.split("");
                map[i] = arr;
            }

            bfsAll();


        }catch (IOException e){}
    }

//    bfs를 도는 queue의 사이즈를 체크해서 그 사이즈 만큼 똑같은 bfs를 돌고나면
//    카운트를 하나씩 해주는 겁니다.

    private static void bfsAll() {
        Queue<Integer[]> queue = new LinkedList<>();
        queue.offer(new Integer[]{0,0});
        map[0][0] = "2";

        while (!queue.isEmpty()) {
            int queueSize = queue.size(); // 2
            for(int i=0;i<queueSize;i++){
                Integer[] vertex = queue.remove();
                bfs(queue, vertex);
            }
            level++;
        }
    }

    private static int bfs(Queue<Integer[]> queue, Integer[] vertex) {
        int offeredCnt = 0;

        if(vertex[1] - 1 >= 0){ // 동
            if(map[vertex[0]][vertex[1] - 1].equals("1")){
                queue.offer(new Integer[]{vertex[0], vertex[1] - 1});
                map[vertex[0]][vertex[1] - 1] = "2";
                offeredCnt++;
            }
        }
        if(vertex[1] + 1 < m){ // 서
            if(map[vertex[0]][vertex[1] + 1].equals("1")){
                queue.offer(new Integer[]{vertex[0], vertex[1] + 1});
                map[vertex[0]][vertex[1] + 1] = "2";
                offeredCnt++;
            }
        }
        if(vertex[0] + 1 < n){ // 남
            if(map[vertex[0] + 1][vertex[1]].equals("1")){
                queue.offer(new Integer[]{vertex[0] + 1, vertex[1]});
                map[vertex[0] + 1][vertex[1]] = "2";
                offeredCnt++;
            }
        }
        if(vertex[0] - 1 >= 0){ // 북
            if(map[vertex[0] - 1][vertex[1]].equals("1")){
                queue.offer(new Integer[]{vertex[0] - 1, vertex[1]});
                map[vertex[0] - 1][vertex[1]] = "2";
                offeredCnt++;
            }
        }
        return offeredCnt;
    }
}
