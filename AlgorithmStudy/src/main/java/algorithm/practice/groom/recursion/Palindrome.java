package algorithm.practice.groom.recursion;

import java.util.Scanner;

public class Palindrome {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        String s = sc.next();

        if (isPalindrome(s)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean isPalindrome(String s) {
        if (s.isEmpty() || s.length() == 1) {
            return true;
        }

        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }

        return isPalindrome(s.substring(1, s.length() - 1));
    }
}
