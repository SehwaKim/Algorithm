package boj11729;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int count;
    static List<Integer[]> route = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        moveGroup(n, 1, 3);
        System.out.println(count);
        route.stream().forEach((pair) -> System.out.println(pair[0] + " " + pair[1]));
    }

    private static void moveGroup(int n, int fromRod, int toRod) {
        count++;
        if (n == 1) {
            route.add(new Integer[]{fromRod, toRod});
            return;
        }
        moveGroup(n - 1, fromRod, 6 - fromRod - toRod); // 가운데 기둥으로 가야 함
        // n번째 원판 목표기둥에 옮겨
        route.add(new Integer[]{fromRod, toRod});
        moveGroup(n - 1, 6 - fromRod - toRod, toRod); // 가운데 있던 원판그룹 목표기둥으로 옮김
    }
}

