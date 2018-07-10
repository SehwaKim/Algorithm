package boj2667;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// 단지 번호 붙이기
public class Main {
    static String[][] map;
    static int numOfDanji;
    static int numOfHouse;
    static List<Integer> houseNumList = new ArrayList<>();

    public static void main(String[] args) {
        setData();
        dfsAll();
        getResult();
    }

    private static void getResult() {
        System.out.println(numOfDanji);
        Collections.sort(houseNumList);
        houseNumList.forEach(System.out::println);
    }

    private static void setData() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            int n = Integer.parseInt(line);
            map = new String[n][];

            for (int i = 0; i < n; i++) {
                line = br.readLine();
                map[i] = line.split("");
            }

        } catch (IOException e) {}
    }

    private static void dfsAll() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if(map[i][j].equals("1")){
                    map[i][j] = "2";
                    numOfHouse++;
                    dfs(new Integer[]{i,j});
                    houseNumList.add(numOfHouse);
                    numOfHouse = 0;
                    numOfDanji++;
                }
            }
        }
    }

    private static int dfs(Integer[] vertex) {
        if(vertex[1] - 1 >= 0){ // 동
            if(map[vertex[0]][vertex[1] - 1].equals("1")){
                map[vertex[0]][vertex[1] - 1] = "2";
                numOfHouse++;
                dfs(new Integer[]{vertex[0], vertex[1] - 1});
            }
        }
        if(vertex[1] + 1 < map.length){ // 서
            if(map[vertex[0]][vertex[1] + 1].equals("1")){
                map[vertex[0]][vertex[1] + 1] = "2";
                numOfHouse++;
                dfs(new Integer[]{vertex[0], vertex[1] + 1});
            }
        }
        if(vertex[0] + 1 < map.length){ // 남
            if(map[vertex[0] + 1][vertex[1]].equals("1")){
                map[vertex[0] + 1][vertex[1]] = "2";
                numOfHouse++;
                dfs(new Integer[]{vertex[0] + 1, vertex[1]});
            }
        }
        if(vertex[0] - 1 >= 0){ // 북
            if(map[vertex[0] - 1][vertex[1]].equals("1")){
                map[vertex[0] - 1][vertex[1]] = "2";
                numOfHouse++;
                dfs(new Integer[]{vertex[0] - 1, vertex[1]});
            }
        }

        return numOfHouse;
    }
}