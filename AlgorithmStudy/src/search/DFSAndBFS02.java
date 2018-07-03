package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DFSAndBFS02 {
    static int numOfNodes;
    static int numOfEdges;
    static int startNode;
    static Map<Integer, Node> registeredNodes = new HashMap<>();
    static List<Node> graph;

    static class Node implements Comparable<Node>{
        int data;
        List<Node> adjacent = new LinkedList<>();
        boolean dfs;
        boolean bfs;

        Node(int data){
            this.data = data;
            this.dfs = false;
            this.bfs = false;
        }

        @Override
        public int compareTo(Node o) {
            int result = this.data - o.data;
            return result;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] arr = line.split(" ");
        numOfNodes = Integer.parseInt(arr[0]);
        numOfEdges = Integer.parseInt(arr[1]);
        startNode = Integer.parseInt(arr[2]);
        graph = new ArrayList<>(numOfNodes);

        for(int i=0; i<numOfEdges; i++){
            line = br.readLine();
            arr = line.split(" ");
            addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
        }

        Node root = registeredNodes.get(startNode);
//        dfs(root);
        dfsR(root);
        System.out.println();
//        bfs(root);
    }

    private static void dfsR(Node node) {
        node.dfs = true;
        visit(node);
        if(node.adjacent.size() > 0){
            Collections.sort(node.adjacent);
            for(Node childNode : node.adjacent){
                if(!childNode.dfs) {
                    childNode.dfs = true;
                    dfsR(childNode);
                }
            }
        }else {
            return;
        }
    }

    private static void dfs(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.dfs = true;

        while(!stack.empty()){
            Node node = stack.pop();
            if(node.adjacent.size() > 0){
                Collections.sort(node.adjacent);
                for(int i=node.adjacent.size()-1; i>=0; i--){
                    if(!node.adjacent.get(i).dfs) {
                        stack.push(node.adjacent.get(i));
                        node.adjacent.get(i).dfs = true;
                    }
                }
            }
            visit(node);
        }
    }

    private static void bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        root.bfs = true;

        while(!queue.isEmpty()){
            Node node = queue.remove();
            for (Node childNode : node.adjacent) {
                if (!childNode.bfs) {
                    queue.offer(childNode);
                    childNode.bfs = true;
                }
            }
            visit(node);
        }
    }

    private static void visit(Node node) {
        System.out.print(node.data);
    }

    private static void addEdge(int data1, int data2) {
        Node node1 = getNode(data1);
        Node node2 = getNode(data2);
        if(!node1.adjacent.contains(node2)){
            node1.adjacent.add(node2);
        }
    }

    private static Node getNode(int data) {
        Node node;

        if(!registeredNodes.containsKey(data)){
            node = new Node(data);
            registeredNodes.put(data, node);
            graph.add(node);
        }else{
            node = registeredNodes.get(data);
        }

        return node;
    }
}
