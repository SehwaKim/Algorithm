package algorithm.practice.carrotbook.ch05;

import java.util.Scanner;

public class Euclidean {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        int x = stdIn.nextInt();
        int y = stdIn.nextInt();

        System.out.println(gcd(x, y));
        System.out.println(gcd2(x, y));
        System.out.println(gcd3(x, y));
    }

    private static int gcd3(int x, int y) {
        while (y != 0) {
            int temp = y;
            y = x % y;
            x = temp;
        }
        return (x);
    }

    // 유클리드 호제법으로 greatest common divisor 구하기 without main.java.algorithm.practice.recursion
    private static int gcd2(int x, int y) {
        int small;
        int big;
        int result;
        if (x > y) {
            small = y;
            big = x;
        } else {
            small = x;
            big = y;
        }
        result = big % small; // 나머지가 있다 == 직사각형이 생겼다!
        big = small;

        while (result > 0) {
            int tmp = result;
            if (big % result > 0) {
                result = big % result;
            } else {
                return result;
            }
            big = tmp;
        }

        return result;
    }

    private static int gcd(int x, int y) {
        if (y == 0)
            return x;
        else
            return gcd(y, x % y);
    }
}
