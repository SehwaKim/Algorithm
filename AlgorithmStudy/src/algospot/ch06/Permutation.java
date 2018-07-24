package algospot.ch06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutation {
    static int tot;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int toPick = sc.nextInt();
        List<Integer> picked = new ArrayList<>();
        pick(n, picked, toPick);
        System.out.println(tot);
    }

    private static void pick(int n, List<Integer> picked, int toPick) {
        if (toPick == 0) {
            System.out.println(picked);
            tot++;
            return;
        }

        for (int next = 0; next < n; next++) {
            if (picked.contains(next))
                continue;

            picked.add(next);
            pick(n, picked, toPick - 1);
            picked.remove(picked.size() - 1);
        }
    }
}
