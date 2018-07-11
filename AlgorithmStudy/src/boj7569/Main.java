package boj7569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int m;
    static int n;
    static int h;
    static List<String[][]> box;
    static Queue<Integer[]> queue = new LinkedList<>();
    static int level = 0;

    public static void main(String[] args) {
        init();
        bfsAll();
        showResult();
    }

    private static void showResult() {
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
        for(int i=0; i<h; i++){
            String[][] layer = box.get(i);
            for(int j=0; j<n; j++){
                for (int w = 0; w < m; w++) {
                    if(layer[j][w].equals("0")){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void bfsAll() {
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                bfs(queue.poll());
            }
            level++;
        }
    }

    private static void bfs(Integer[] vertex) {
        int y = vertex[0];
        int x = vertex[1];
        int z = vertex[2];
        String[][] layer = box.get(z);

        if(x - 1 >= 0){
            if(layer[y][x - 1].equals("0")){
                queue.offer(new Integer[]{y, x - 1, z});
                layer[y][x- 1] = "2";
            }
        }
        if(x + 1 < m){
            if(layer[y][x + 1].equals("0")){
                queue.offer(new Integer[]{y, x + 1, z});
                layer[y][x + 1] = "2";
            }
        }
        if(y + 1 < n){
            if(layer[y + 1][x].equals("0")){
                queue.offer(new Integer[]{y + 1, x, z});
                layer[y + 1][x] = "2";
            }
        }
        if(y - 1 >= 0){
            if(layer[y - 1][x].equals("0")){
                queue.offer(new Integer[]{y - 1, x, z});
                layer[y - 1][x] = "2";
            }
        }
        if (z - 1 >= 0) {
            String[][] below = box.get(z - 1);
            if (below[y][x].equals("0")) {
                queue.offer(new Integer[]{y, x, z - 1});
                below[y][x] = "2";
            }
        }
        if (z + 1 < h) {
            String[][] above = box.get(z + 1);
            if (above[y][x].equals("0")) {
                queue.offer(new Integer[]{y, x, z + 1});
                above[y][x] = "2";
            }
        }
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            String[] arr = line.split(" ");
            m = Integer.parseInt(arr[0]);
            n = Integer.parseInt(arr[1]);
            h = Integer.parseInt(arr[2]);
            box = new ArrayList<>(h);

            for(int i=0; i<h; i++){
                String[][] map = new String[n][];
                for (int j = 0; j < n; j++) {
                    line = br.readLine();
                    map[j] = line.split(" ");

                    for (int w = 0; w < map[j].length; w++) {
                        if (map[j][w].equals("1")) {
                            queue.offer(new Integer[]{j, w, i}); // y,x,z
                        }
                    }

                }
                box.add(map);
            }

        }catch (IOException e){}
    }
}
