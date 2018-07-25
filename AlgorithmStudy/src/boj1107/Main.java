package boj1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 100 에서 N 으로 가기위해서 몇 단계를 거쳐야 하는지 (1476과 아이디어 동일)
        // 완전 탐색을 해라
        // 이동할 수 있는 방법을 나눈다
        // 1. 숫자로 한방에
        // 2. 근접한 숫자로 가서 +++ 혹은 ---
        // 3. +++으로만 or ---으로만

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String nStr = br.readLine().trim();
            int digit = nStr.length();
            char[] charArray = nStr.toCharArray();
            int [] ints = new int[digit];
            int n = 0;
            for (char c : charArray) {
                ints[n] = parseInt(c+"");
                n++;
            }
            int N = parseInt(nStr);
            int M = parseInt(br.readLine().trim());
            boolean[] brokenBtn = new boolean[12]; // 0~9, + -> 10, - -> 11
            if (M > 0) {
                String[] str = br.readLine().split("\\s");
                for (int i = 0; i < str.length; i++) {
                    Character c = new Character(str[i].charAt(0));
                    if (c == '+') {
                        brokenBtn[10] = true;
                    }
                    if (c == '-') {
                        brokenBtn[11] = true;
                    }
                    brokenBtn[parseInt(c+"")] = true;
                }
            }

            int level = 0;
            int curr = 100;
            int[] picked = new int[digit];
            List<Integer> levels = new ArrayList<>();

            // 1. 숫자로 한방에
            if (curr == N) {
                System.out.println(level);
                return;
            }

            // 2. 근접해서 + or -
            for (int i = 0; i < ints.length; i++) { // 자리수만큼
                if (!brokenBtn[ints[i]]) {
                    picked[i] = ints[i];
                    level++;
                } else {
                    int amount = 1;
                    while (true) {
                        boolean available = false;
                        if (ints[i] + amount < brokenBtn.length) {
                            if (!brokenBtn[ints[i] + amount]) {
                                picked[i] = ints[i] + amount;
                                level++;
//                              System.out.println("+"+amount);
                                available = true;
                            }
                        }
                        if ((ints[i] - amount) > 0) {
                            if (!brokenBtn[ints[i] - amount]) {
                                if (!available) {
                                    picked[i] = ints[i] - amount;
                                    level++;
//                                    System.out.println("-"+amount);
                                    available = true;
                                }
                            }
                        }
                        if (available) {
                            break;
                        }
                        amount++;
                    }
                }
            }// 이 for 문이 끝나면 picked 에 값이 다 채워진 상태 혹은 쓸 수 있는 숫자버튼이 없으면 아무것도 안채워진 상태

//            System.out.println("====="+level);
            StringBuilder builder = new StringBuilder();
            for (int num : picked) {
                builder.append(num);
            }
            int incompleteNum = parseInt(builder.toString());
//            System.out.println("incompleteNum: "+incompleteNum);
            level += Math.abs(incompleteNum - N);
            levels.add(level);
            level = 0;

            // 3. +, - 으로만
            if (!brokenBtn[10] || !brokenBtn[11]) {
                level += Math.abs(100 - N);
                levels.add(level);
            }

            Collections.sort(levels);
//            System.out.println(levels);
            System.out.println(levels.get(0));


        }catch (IOException e){}
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
