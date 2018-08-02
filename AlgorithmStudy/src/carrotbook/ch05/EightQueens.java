package carrotbook.ch05;

public class EightQueens {
    static int[] pos = new int[8];
    static boolean[] flag = new boolean[8];
    static boolean[] flag_a = new boolean[15];
    static boolean[] flag_b = new boolean[15];
    static int count;

    public static void main(String[] args) {
        // 요구사항: 8x8 맵에 8개의 퀸을 나열하는 조합 구하기
        // 덩어리문제: 각 열에 퀸을 1개만 배치하라
        // 전체문제를 부분문제로 나누기
        // n열에 대해 각 행에 퀸을 배치하라 --> set(열)
        // 분기 한정: 각 행에 하나의 퀸이 와야 함 (중복 되는 행 없게 배치)

        set(0);
        System.out.println(count);
    }

    private static void set(int i) {
        if (i == 8) {
            count++;
            return;
        }
        for (int j = 0; j < 8; j++) {
            if (!flag[j] && !flag_a[i + j] && !flag_b[i - j + 7]) {
                pos[i] = j; // i열 j행에 퀸 배치 - 부분문제 8개가 덩어리문제 1개 해결
                flag[j] = flag_a[i + j] = flag_b[i - j + 7] = true;
                set(i + 1); // 다음 열에 배치할 set 메소드 호출
                flag[j] = flag_a[i + j] = flag_b[i - j + 7] = false;
            }
        }
    }
}
