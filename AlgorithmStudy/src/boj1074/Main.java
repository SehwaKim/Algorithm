package boj1074;

import java.util.Scanner;

public class Main {
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt(); // r, c

        int length = (int) Math.pow(2, n);


        divide(r, c, 0, 0, length);
        System.out.println(count);

    }

    private static void divide(int r, int c, int startY, int startX, int length) {
        int lastY = startY + length; // exclusive
        int lastX = startX + length;

        if (r < lastY - (length / 2) && c < lastX - (length / 2)) { // 1
            if (length > 2) {
                divide(r, c, startY, startX, length / 2);
            }
        }
        if (r < lastY - (length / 2) && c >= lastX - (length / 2)) { // 2
            count += (length / 2) * (length / 2);
            if (length > 2) {
                divide(r, c, startY, lastX - (length / 2), length / 2);
            }
        }
        if (r >= lastY - (length / 2) && c < lastX - (length / 2)) { // 3
            count += ((length / 2) * (length / 2)) * 2;
            if (length > 2) {
                divide(r, c, lastY - (length / 2), startX, length / 2);
            }
        }
        if (r >= lastY - (length / 2) && c >= lastX - (length / 2)) { // 4
            count += ((length / 2) * (length / 2)) * 3;
            if (length > 2) {
                divide(r, c, lastY - (length / 2), lastX - (length / 2), length / 2);
            }
        }
    }
}
