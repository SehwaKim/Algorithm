package boj1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSAndBFS02 {
    private static Map<Integer, Node> registry = new HashMap<>();
    private static List<Node> nodes;

    static class Node implements Comparable<Node> {
        int data;
        boolean marked;
        List<Node> adjacent;

        Node(int data){
            this.data = data;
            this.marked = false;
            adjacent = new ArrayList<>();
        }

        @Override
        public int compareTo(Node o) {
            return this.data - o.data;
        }
    }

    public static void main(String[] args) {
        int numOfVertices;
        int numOfEdges;
        int s=0;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            String line = br.readLine();
            String[] arr = line.split(" ");
            numOfVertices = Integer.parseInt(arr[0]);
            numOfEdges = Integer.parseInt(arr[1]);
            s = Integer.parseInt(arr[2]);
            nodes = new ArrayList<>(numOfVertices);

            for(int i=0;i<numOfEdges;i++){
                line = br.readLine();
                arr = line.split(" ");
                Node node1 = getNode(Integer.parseInt(arr[0]));
                Node node2 = getNode(Integer.parseInt(arr[1]));
                node1.adjacent.add(node2);
                node2.adjacent.add(node1);
            }
        }catch (IOException e){}

        Node root = registry.get(s);

        dfs(root);

        System.out.println();

        for(Node node : nodes){
            node.marked = false;
        }

        bfs(root);

    }

    private static void dfs(Node node) {
        node.marked = true;
        visit(node);
        Collections.sort(node.adjacent);
        for(Node childNode : node.adjacent){
            if(!childNode.marked){
                dfs(childNode);
            }
        }
    }

    private static void bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        root.marked = true;
        while(!queue.isEmpty()){
            Node node = queue.remove();
            for(Node childNode : node.adjacent){
                if(!childNode.marked){
                    queue.offer(childNode);
                    childNode.marked = true;
                }
            }
            visit(node);
        }
    }

    private static void visit(Node node) {
        System.out.print(node.data+" ");
    }

    private static Node getNode(int i) {
        if(!registry.containsKey(i)){
            Node newOne = new Node(i);
            nodes.add(newOne);
            registry.put(i, newOne);
            return newOne;
        }else{
            return registry.get(i);
        }
    }
}
