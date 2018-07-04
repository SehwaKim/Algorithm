package boj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSAndBFS03 {
    static List<List<Integer>> adjacency = new ArrayList<>(1001);
    static boolean[] dfs_visited = new boolean[1001];
    static boolean[] bfs_visited = new boolean[1001];

    public static void main(String[] args) {
        int numOfVertices;
        int numOfEdges;
        int s=0;

        for(int i=0;i<1001;i++){
            adjacency.add(new ArrayList<>());
        }

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String line = br.readLine();
            String[] arr = line.split(" ");
            numOfVertices = Integer.parseInt(arr[0]);
            numOfEdges = Integer.parseInt(arr[1]);
            s = Integer.parseInt(arr[2]);

            for(int i=0;i<numOfEdges;i++){
                line = br.readLine();
                arr = line.split(" ");
                int vertex1 = Integer.parseInt(arr[0]);
                int vertex2 = Integer.parseInt(arr[1]);
                adjacency.get(vertex1).add(vertex2);
                adjacency.get(vertex2).add(vertex1);
            }
        }catch (IOException e){}

        dfs(s);
        System.out.println();
        bfs(s);
    }

    private static void dfs(int s) {
        dfs_visited[s] = true;
        visit(s);
        Collections.sort(adjacency.get(s));
        for(int adj : adjacency.get(s)){
            if(!dfs_visited[adj]){
                dfs(adj);
            }
        }
    }

    private static void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        bfs_visited[s] = true;

        while (!queue.isEmpty()){
            int vertex = queue.remove();
            for(int adj : adjacency.get(vertex)){
                if(!bfs_visited[adj]){
                    queue.offer(adj);
                    bfs_visited[adj] = true;
                }
            }
            visit(vertex);
        }
    }

    private static void visit(int s) {
        System.out.print(s+" ");
    }
}
