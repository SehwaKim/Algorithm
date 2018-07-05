package boj1697;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

//    큐에 삽입할 때, x-1, x+1, x*2 세가지 경우와 그 시점에 대한 시간을 함께 넣어주면서
//    BFS 를 탐색하면 된다.
//    visited 를 체크해주지 않았을땐 트리가 무한정 넓어져서 시간초과뜸
//    visited 로 한번 찍었던 위치는 다시 못찍도록 했음
//    항상 경우의 수로 생각하자.

    private static int start;
    private static int destination;
    private static int level;
    private static boolean reached;
    static int[] dx = { -1, 1, 0 };
    private static boolean[] visited = new boolean[100001];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            String[] arr = line.split(" ");
            start = Integer.parseInt(arr[0]);
            destination = Integer.parseInt(arr[1]);

            bfs();

            System.out.println(level-1);

        }catch (IOException e){}
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()){
            int queueSize = queue.size();

            for(int i=0; i<queueSize; i++){
                int pos = queue.poll();

                if(pos == destination){
                    reached = true;
                    break;
                }

                for(int j=0; j<dx.length; j++){
                    int next;

                    if(dx[j] != 0){
                        next = pos + dx[j];
                    }else {
                        next = pos * 2;
                    }

                    if (0 <= next && next <= 100000) {
                        if (!visited[next]) {
                            queue.add(next);
                            visited[next] = true;
                        }
                    }
                }
            }

            level++;

            if(reached){
                break;
            }
        }
    }
}
