package boj1874;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] series = new int[n];
        for (int i = 0; i < n; i++) {
            series[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        List<Character> stackLog = new ArrayList<>();
        int nextInt = 1;

        for (int i = 0; i < n; i++) {
            int target = series[i];

            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                stackLog.add('-');
            } else {
                int pushTime = target - nextInt + 1;
                if (target < nextInt) {
                    System.out.println("NO");
                    return;
                }
                for (int j = 0; j < pushTime; j++) {
                    stack.push(nextInt++);
                    stackLog.add('+');
                }
                stack.pop();
                stackLog.add('-');
            }
        }

        stackLog.forEach(System.out::println);
    }
}
