public class Node {
    //단방향 LinkedList
    //하나의 node는 데이터와 다음 node의 참조값을 가짐
    int data;
    Node next = null;

    //노드의 데이터 초기화
    public Node(int d){
        this.data = d;
    }

    //node 추가
    public void append(int d){
        Node end = new Node(d);
        Node n = this;
        while(n.next != null){
            n = n.next;
        }
        n.next = end;
    }

    //node 삭제
    public void delete(int d){
        Node n = this;
        while(n.next != null){
            if(n.next.data == d){
                n.next = n.next.next;
            }else{
                n = n.next;
            }
        }
    }

    //리스트 출력
    public void retrieve(){
        Node n = this;
        while(n.next != null){
            System.out.print(n.data+" -> ");
            n = n.next;
        }
        System.out.print(n.data);
    }
}