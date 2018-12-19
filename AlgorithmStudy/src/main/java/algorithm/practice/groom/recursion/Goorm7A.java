package algorithm.practice.groom.recursion;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Goorm7A {
    public static final Scanner sc = new Scanner(System.in);
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static final char[] ITEMS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static int count;

    public static void main(String[] args) throws Exception {
        // 알파벳중에서 서로다른 N개의 문자를 뽑는 '조합의 수' 구하기
        // 알파벳의 순서는 고려하지 않는다, 즉 같은 알파벳을 사용한 조합은 모두 하나의 경우의 수로 생각한다.

        int n = sc.nextInt();

        // 이것이 구현되어야 한다
        // for (int i = 0; i < 26; i++)
        //      for (int j = i + 1; j < 26; j++)
        //           for (int w = j + 1; w < 26; w++)
        //              System.out.printf("%c%c%c\n", ITEMS[i], ITEMS[j], ITEMS[w]);


        // 하나의 for 문을 함수라고 여기고 호출해보자
        findAllCombinations(1, n, new ArrayList<Integer>());

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
    }

    private static void findAllCombinations(int depth, int limit, List<Integer> selectedIndex) throws IOException {

        if (depth > limit) {
            for (int i = 0; i < selectedIndex.size(); i++) { // selectedIndex 에는 항상 limit 만큼의 요소들이 있어야 한다
                bw.write(ITEMS[selectedIndex.get(i)]);
            }
            bw.write("\n");
            count++;
            return;
        }

        int lastChosen = -1;
        if (!selectedIndex.isEmpty()) {
            lastChosen = selectedIndex.get(selectedIndex.size() - 1);
        }

        // 이렇게 달라지는 부분은 파라미터를 통해 일반화했다 즉 달라지는 부분은 파라미터에게 맡겼다
        for (int i = lastChosen + 1; i < 26; i++) {
            selectedIndex.add(i);
            findAllCombinations(depth + 1, limit, selectedIndex);
            selectedIndex.remove(selectedIndex.size() - 1);
        }

        // 순열과 달리 조합에서는
        // 형제 노드로 옮겨갈 때 이전 상태의 결정값은 재등장하지 못하게 해야한다 (중복 제거)
    }
}

