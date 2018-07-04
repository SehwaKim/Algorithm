package boj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class DFSAndBFS {
    static int numOfNodes;
    static int numOfEdges;
    static int s;
    static int[][] adjacency;
    static final int UNVISITED = 1;
    static final int VISITED = 2;
    static boolean[] dfs_visited = new boolean[1001];
    static boolean[] bfs_visited = new boolean[1001];


    public static void main(String[] args) {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String line = br.readLine();
            String[] arr = line.split(" ");
            numOfNodes = Integer.parseInt(arr[0]);
            numOfEdges = Integer.parseInt(arr[1]);
            s = Integer.parseInt(arr[2]);
            adjacency = new int[1001][1001];

            for(int i=0; i<numOfEdges; i++){
                line = br.readLine();
                arr = line.split(" ");
                int data1 = Integer.parseInt(arr[0]);
                int data2 = Integer.parseInt(arr[1]);
                addEdge(data1, data2);
            }
        }catch (IOException e){}

        dfs(s);
        System.out.println();
        bfs(s);
    }

    private static void dfs(int s) {
        dfs_visited[s] = true;
        visit(s);
        for(int i=0;i<1001;i++){
            if(adjacency[s][i] == UNVISITED && !dfs_visited[i]){
                adjacency[i][s] = VISITED;
                dfs(i);

            }else if(adjacency[s][i] == VISITED){
                adjacency[s][i] = UNVISITED;
            }
        }
    }

    private static void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        bfs_visited[s] = true;

        while (!queue.isEmpty()) {
            int vertex = queue.remove();

            for(int i=0;i<1001;i++){
                if(adjacency[vertex][i] == UNVISITED && !bfs_visited[i]){
                    adjacency[vertex][i] = adjacency[i][vertex] = VISITED;
                    queue.offer(i);
                    bfs_visited[i] = true;
                }
            }
            visit(vertex);
        }
    }

    private static void visit(int s) {
        System.out.print(s+" ");
    }

    private static void addEdge(int data1, int data2) {
        adjacency[data1][data2] = adjacency[data2][data1] = UNVISITED;
    }
}
