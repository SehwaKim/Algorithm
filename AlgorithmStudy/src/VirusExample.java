import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class VirusExample {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int computer = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> networks = new ArrayList<>();
        for (int i=0; i<=computer*2; i++) {
            networks.add(new ArrayList<>());
        }
        for (int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int com1 = Integer.parseInt(st.nextToken());
            int com2 = Integer.parseInt(st.nextToken());
            networks.get(com1).add(com2);    //com1 -> com2
            networks.get(com2).add(com1);    //com2 -> com1 양방향 네트워크로 
        }

        Virus virus = new Virus(networks);
        System.out.println(virus.getInfectionCount());
    }

    static class Virus {
        ArrayList<ArrayList<Integer>> networks = new ArrayList<>();

        public Virus(ArrayList<ArrayList<Integer>> networks) {
            this.networks = networks;
        }

        public int getInfectionCount() {
            int count = 0;
            boolean[] visited = new boolean[networks.size()];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(1);
            visited[1] = true;
            while (!queue.isEmpty()) {
                int computer = queue.poll();
                ArrayList<Integer> infections = networks.get(computer);
                for (int i=0; i<infections.size(); i++) {
                    if (!visited[infections.get(i)]) {
                        count++;
                        queue.offer(infections.get(i));
                        visited[infections.get(i)] = true;
                    }
                }
            }
            return count;
        }
    }
}