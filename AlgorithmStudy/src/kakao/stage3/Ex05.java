package kakao.stage3;

import java.util.*;

public class Ex05 {
    public static void main(String[] args) {
//        String[] words = {"go", "gone", "guild"};
        String[] words = { "abc", "def", "ghi", "jklm"}; // 4
//        String[] words = {"word", "war", "warrior", "world"}; // 15
        int a = new Ex05().solution(words);
        System.out.println(a);
    }

    public int solution(String[] words) {
        Map<String, Node> firstNodeOfWord = new HashMap<>();
        for (String word : words) {
            char prev = word.charAt(0);
            if (!firstNodeOfWord.containsKey(prev + "")) {
                firstNodeOfWord.put(prev + "", new Node(prev));
            } else {
                firstNodeOfWord.get(prev + "").siblingExist = true;
            }
            Node prevNode = firstNodeOfWord.get(prev + "");

            for (int i = 1; i < word.length(); i++) {
                char c = word.charAt(i);
                // prev 의 자식에 이미 등록되있나 검사
                Node node;
                if (!prevNode.children.containsKey(c + "")) {
                    node = new Node(c, prevNode);
                    prevNode.addChild(c, node);
                } else {
                    node = prevNode.children.get(c + "");
                    node.siblingExist = true;
                }
                prevNode = node;
            }
        }

        // 잘 저장됬나
        /*for (String k : firstNodeOfWord.keySet()) {
            Node node = firstNodeOfWord.get(k);
            printChild(node);
        }*/
        int answer = 0;

        for (String word : words) {
            answer++; // 첫글자는 무조건 쳐야 하니까...
            Node prevNode = firstNodeOfWord.get(word.charAt(0) + "");
            if (!prevNode.siblingExist) {
                continue;
            }
            for (int i = 1; i < word.length(); i++) {
                if (prevNode.children.size() == 1 && !prevNode.children.get(word.charAt(i)+"").siblingExist) {
                    answer++;
                    break;
                } else {
                    answer++;
                    prevNode = prevNode.children.get(word.charAt(i) + "");
                    if (!prevNode.siblingExist) {
                        break;
                    }
                }
            }
        }

        return answer;
    }

    /*private void printChild(Node node) {
        System.out.println(node.data);
        if (node.children.size() == 0) {
            return;
        }
        for (String childKey : node.children.keySet()) {
            printChild(node.children.get(childKey));
        }
    }*/

    class Node {
        String data;
        Node parent;
        Map<String, Node> children = new HashMap<>();
        boolean siblingExist;

        public Node(char data) {
            this.data = data + "";
        }

        public Node(char data, Node parent) {
            this.data = data + "";
            this.parent = parent;
        }

        public void addChild(char c, Node child) {
            children.put(c + "", child);
            if (child.parent != this) {
                child.parent = this;
            }
        }

        @Override
        public int hashCode() {
            return data.hashCode();
        }
    }
}
