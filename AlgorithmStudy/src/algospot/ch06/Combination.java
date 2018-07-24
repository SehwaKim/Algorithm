package algospot.ch06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Combination {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int toPick = sc.nextInt();
        List<Integer> picked = new ArrayList<>(toPick);
        pick(n, picked, toPick);
    }

    private static void pick(int n, List<Integer> picked, int toPick) {
        if (toPick == 0) {
            print(picked);
            return;
        }

        int smallest = picked.isEmpty() ? 0 : picked.get(picked.size() - 1) + 1;

        for (int next = smallest; next < n; next++) {
            picked.add(next);
            pick(n, picked, toPick - 1);
            picked.remove(picked.size() - 1);
        }
    }

    private static void print(List<Integer> picked) {
        System.out.println(picked);
    }
}
