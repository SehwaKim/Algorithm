package algorithm.practice.groom.recursion;

import java.util.ArrayList;
import java.util.List;

public class Prac01_1 {
    static int count;

    public static void main(String[] args) {
        //for (int i = 0; i < 26; i++) {
        //            // 첫번째 글자 A~Z
        //            for (int j = 0; j < 26; j++) {
        //                // 두번째 글자 A~Z
        //                for (int w = 0; w < 26; w++) {
        //                    // 세번째 글자 A~Z
        //                    System.out.printf("%c%c%c\n", 'A' + i, 'A' + j, 'A' + w);
        //                }
        //            }
        //        }

        // 위의 코드를 재귀함수 호출로 바꿔보자
        // 하나의 for 문을 함수라고 여기고 호출해보자
        int depth = 1;
        int limit = 3;
        choose(depth, limit, new ArrayList<>());

        System.out.println(count);
    }

    private static void choose(int depth, int limit, List<Integer> selectedIndex) {

        if (depth > limit) {
            // System.out.printf("%c%c%c\n", 'A' + i, 'A' + j, 'A' + w);
            // 어떻게 i, j, w 를 알지? 어떻게 앞의 상태들을 알지?

            // 다중 for 문에서는 같은 큰 블럭내에 있는 지역변수니까 이전 상태가 결정했던 값에 바로 접근할 수 있다
            // i , j , w 를 바로 갖다 쓸 수 가 있다.
            // 하지만 함수는? 함수와 함수 사이에 값을 전달하려면 파라미터로 전달하는 수 밖에 없다.
            for (int i = 0; i < selectedIndex.size(); i++) {
                if (i > 0) {
                    System.out.print(" ");
                }
                System.out.print((char) ('A' + selectedIndex.get(i)));
            }
            System.out.println();
            count++;
            return;
        }

        // 그러면 이 함수는 for 문이 대체된거니까 for 문을 여기에 적자...

        for (int i = 0; i < 26; i++) {
            int chosen = i; // 이 상태에서는 i를 선택했다
            // 그럼 이 선택을 아래의 함수가 내부에서 계속 알아야 한다 (누적해서) - 배열에 저장해서 알게하자
            selectedIndex.add(chosen);
            choose(depth + 1, limit, selectedIndex);
            selectedIndex.remove(selectedIndex.size() - 1);
            // chosen 삭제 - 현상태의 형제 노드로 옮겨가야 하니깐 현 상태의 결정은 삭제하고 그 이전까지의 결정만 가지고 다시 시작
        }
    }
}
