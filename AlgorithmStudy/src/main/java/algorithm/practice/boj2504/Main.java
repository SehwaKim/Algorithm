package algorithm.practice.boj2504;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<Character, Character> pairMap = new HashMap<>();
    static List<Character> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        if (str.length() % 2 != 0) {
            System.out.println(0);
            return;
        }

        pairMap.put('(', ')');
        pairMap.put(')', '(');
        pairMap.put('[', ']');
        pairMap.put(']', '[');

        stack = new ArrayList<>(str.length());

        System.out.println(getValueOfBracket(str, 0));
    }

    private static int getValueOfBracket(String str, int start) {
        int res = 1;

        if (start == str.length() - 1) {
            return 1;
        }

        Character half = str.charAt(start);

        if (stack.isEmpty()) {
            stack.add(half);
            res *= getValueOfBracket(str, start + 1);
        }

        if (!stack.isEmpty()) {
            Character peeked = stack.get(stack.size() - 1);

            if (peeked.equals(pairMap.get(half))) {
                stack.remove(stack.size() - 1);
                return half.equals('(') ? 2 : 3;
            }

            res *= getValueOfBracket(str, start + 1);
        }


        return half.equals('(') ? 2 * res : 3 * res;
    }
}
