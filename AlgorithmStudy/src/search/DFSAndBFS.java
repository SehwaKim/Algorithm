package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class DFSAndBFS {
    static int numOfNodes;
    static int numOfEdges;
    static int startNode;

    public static void main(String[] args) throws IOException{
        // 입력받기
        // 정점갯수 간선갯수 시작노드 ex) 4 5 1
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String[] arr = line.split(" ");
        numOfNodes = Integer.parseInt(arr[0]);
        numOfEdges = Integer.parseInt(arr[1]);
        startNode = Integer.parseInt(arr[2]);

        // 두 정점 입력 받기
        Node[] graph = new Node[numOfNodes];
        List<Integer> list = null;
        for(int i=0; i<numOfEdges; i++){
            line = br.readLine();
            arr = line.split(" ");
            int data1 = Integer.parseInt(arr[0]);
            int data2 = Integer.parseInt(arr[1]);
            addEdge(data1, data2);
        }
    }

    private static void addEdge(int data1, int data2) {

    }

    static class Node{
        int data;
        LinkedList<Node> adjacent = new LinkedList<>();
        Node(int data){
            this.data = data;
        }
    }
}
