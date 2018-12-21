package algorithm.practice.groom.recursion;

import java.util.Scanner;
import java.util.Stack;

public class Goorm7B {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int n = sc.nextInt();

        Stack<Disk> A = new Stack<>();
        Stack<Disk> B = new Stack<>();
        Stack<Disk> C = new Stack<>();

        // 첫 번째 기둥에 N개의 원판을 쌓는다
        for (int i = n; i >= 1; i -= 1) {
            Disk d = new Disk(i);
            A.push(d);
        }

        int answer = getMinimumMove(n, A, B, C);

        System.out.println(answer);

    }

    public static int getMinimumMove(int num, Stack<Disk> from, Stack<Disk> buffer, Stack<Disk> to) {
        int move = 0;

        // 재귀함수가 유한번 안에 호출이 종료됨을 보장해야 한다
        if (num == 0) {
            return 0; // 아무 move 도 안생기니깐
        }

        // num-1 그룹을 buffer 에 옮기기
        move += getMinimumMove(num - 1, from, to, buffer);

        // 가장 큰 원반을 목표기둥에 옮기기 - 각 함수의 상태에서는 가장 큰 원반의 이동만을 신경쓴다.
        Disk largest = from.pop();
        to.push(largest);
        move++;

        // buffer 에 있는 num-1 그룹을 목표기둥으로 옮긴다
        move += getMinimumMove(num - 1, buffer, from, to);

        return move;
    }

    static class Disk {
        final int height;

        public Disk(int height) {
            this.height = height;
        }
    }

}
