package algospot.ch06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Picnic {
    static int[] kidsByCase;
    static int[] pairsByCase;
    static List<boolean[][]> cases = new ArrayList<>();

    public static void main(String[] args) {
        init();

        for (int i = 0; i < cases.size(); i++) {
            boolean[][] areFriends = cases.get(i);
            int numOfKids = kidsByCase[i];
            boolean[] taken = new boolean[numOfKids];
            System.out.println(countPairing(areFriends, taken));
        }
    }

    private static int countPairing(boolean[][] areFriends, boolean[] taken) {
        int firstFree = -1;
        for (int i = 0; i < taken.length; i++) {
            if (!taken[i]) {
                firstFree = i;
                break;
            }
        }

        // 기저사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
        if (firstFree == -1) {
            return 1;
        }

        int ret = 0;

        // 이 학생과 짝지을 학생을 결정한다.
        for (int pairWith = firstFree + 1; pairWith < taken.length; pairWith++) {
            if (!taken[pairWith] && areFriends[firstFree][pairWith]) {
                taken[firstFree] = taken[pairWith] = true;
                ret += countPairing(areFriends, taken);
                taken[firstFree] = taken[pairWith] = false;
            }
        }

        return ret;
    }

    private static void init() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int testCase = parseInt(br.readLine().trim());
            kidsByCase = new int[testCase];
            pairsByCase = new int[testCase];
            for (int i = 0; i < testCase; i++) {
                String[] info = br.readLine().split("\\s");
                kidsByCase[i] = parseInt(info[0]);
                pairsByCase[i] = parseInt(info[1]);

                String[] pairs = br.readLine().split("\\s");

                boolean[][] adj = new boolean[10][10];
                for (int j = 0; j < pairs.length; j+=2) {
                    int y = parseInt(pairs[j]);
                    int x = parseInt(pairs[j + 1]);
                    adj[y][x] = true;
                    adj[x][y] = true;
                }
                cases.add(adj);
            }

        }catch (IOException e) {}
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
