package boj1325;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] token = br.readLine().split("\\s");
        int n = parseInt(token[0]);
        int m = parseInt(token[1]);
        Map<Integer, List<Integer>> relationship =new HashMap<>(); // 신뢰 당하는 쪽이 key
        while (m-- > 0) {
            token = br.readLine().split("\\s");
            if (relationship.containsKey(parseInt(token[1]))) {
                relationship.get(parseInt(token[1])).add(parseInt(token[0]));
            } else {
                relationship.put(parseInt(token[1]), new ArrayList<>());
                relationship.get(parseInt(token[1])).add(parseInt(token[0]));
            }
        }

        // 완전 탐색
        int max = 0;
        Map<Integer, List<Integer>> map = new HashMap<>(); // 등록된 정답 후보들 - 최종에선 여기서 젤 큰 키값이 답이 되겠지
        for (int i = 1; i <= n; i++) { // n부터 1번까지
            boolean[] checked = new boolean[n + 1];
            checked[i] = true;
            int hackableNum = getHackableNum(i, relationship, checked);
            if (max <= hackableNum) {
                max = hackableNum;
                if (map.containsKey(max)) {
                    map.get(max).add(i);
                } else {
                    map.put(max, new ArrayList<>());
                    map.get(max).add(i);
                }
            }
        }

        // 출력
        Collections.sort(map.get(max));
        StringBuilder sb = new StringBuilder();
        map.get(max).forEach(i -> sb.append(" " + i));
        System.out.println(sb.toString().substring(1));
    }

    private static int getHackableNum(int n, Map<Integer, List<Integer>> relationship, boolean[] checked) {
        if (!relationship.containsKey(n)) { // 신뢰 하나도 안당하면
            return 1;
        }

        int trustedSize = 1; // 자기 자신부터 세기

        for (Integer newTarget : relationship.get(n)) {
            if (checked[newTarget]) {
                continue;
            }
            checked[newTarget] = true;
            trustedSize += getHackableNum(newTarget, relationship, checked);
        }

        return trustedSize;
    }
}
