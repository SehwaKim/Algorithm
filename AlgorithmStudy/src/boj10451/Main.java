package boj10451;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    static int t;
    static List<List<List<Integer>>> adjLists;
    static List<boolean[]> visitedList;
    static int[] numOfCycle;

    public static void main(String[] args) {
        init();
        dfsAll();
        showResult();
    }

    private static void showResult() {
        for (int num : numOfCycle) {
            System.out.println(num);
        }
    }

    private static void dfsAll() {
        for (int i = 0; i < adjLists.size(); i++) {
            List<List<Integer>> list = adjLists.get(i);

            for (int j = 1; j < list.size(); j++) {//0~8 size 9
                List<Integer> adj = list.get(j);
                for (int w = 0; w < adj.size(); w++) {
                    int vertex = adj.get(w);
                    int start = vertex;
                    boolean[] visited = visitedList.get(i);
                    if (!visited[vertex]) {
                        dfs(list, vertex, start, visited);
                        numOfCycle[i]++;
                    }
                }
            }
        }
    }

    private static void dfs(List<List<Integer>> list, int vertex, int start, boolean[] visited) {
        visited[vertex] = true;
        List<Integer> adj = list.get(vertex);
        for (int i = 0; i < adj.size(); i++) {
            int next = adj.get(i);
            if (next == start) {
                return;
            }
            if (!visited[next]) {
                dfs(list, next, start, visited);
            }
        }
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            t = Integer.parseInt(line);
            adjLists = new ArrayList<>(t);
            numOfCycle = new int[t];
            visitedList = new ArrayList<>(t);

            for (int i = 0; i < t; i++) {
                int length = Integer.parseInt(br.readLine());

                visitedList.add(new boolean[length + 1]);
                adjLists.add(new ArrayList<>(length + 1));
                List<List<Integer>> adjList = adjLists.get(i);

                for (int j = 0; j < length + 1; j++) {
                    adjList.add(new LinkedList<>());
                }

                line = br.readLine();

                String[] row = line.split(" ");

                for (int j = 0; j < row.length; j++) {
                    List<Integer> adj = adjList.get(j + 1);
                    adj.add(Integer.parseInt(row[j]));
                }
            }

        } catch (IOException e) {}
    }
}
