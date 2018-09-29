package nhn;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cardCnt = Integer.parseInt(br.readLine());
        int shuffle = Integer.parseInt(br.readLine());
        int[] n = new int[shuffle];
        for (int i = 0; i < shuffle; i++) {
            n[i] = Integer.parseInt(br.readLine());
        }

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < cardCnt; i++) {
            list.add(i + 1);
        }

        for (int i = 0; i < shuffle; i++) {
            doShuffle(list, n[i], list.size());
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(list.get(i));
        }
    }

    private static void doShuffle(LinkedList<Integer> list, int N, int targetSize) {
        int start = N;
        int end = targetSize - N;
        List<Integer> tempList = new ArrayList<>();
        for (int i = end - 1; i >= start; i--) {
            tempList.add(list.remove(i));
        }

        for (int i = 0; i < tempList.size(); i++) {
            list.addFirst(tempList.get(i));
        }

        int max = 2 * N;
        if (targetSize - max > max) {
            targetSize = tempList.size();
            doShuffle(list, N, targetSize);
        }
    }
}
