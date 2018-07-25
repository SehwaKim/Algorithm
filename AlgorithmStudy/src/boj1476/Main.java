package boj1476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] str = br.readLine().split("\\s");
            final int E = Integer.parseInt(str[0]);
            final int S = Integer.parseInt(str[1]);
            final int M = Integer.parseInt(str[2]);

            int e = 1;
            int s = 1;
            int m = 1;
            int year = 1;
            while (true) {
                if (e == E && s == S && m == M) {
                    System.out.println(year);
                    break;
                }

                e++;
                s++;
                m++;

                if (e == 16) {
                    e = 1;
                }
                if (s == 29) {
                    s = 1;
                }
                if (m == 20) {
                    m = 1;
                }

                year++;
            }

        } catch (IOException e) {}
    }
}
