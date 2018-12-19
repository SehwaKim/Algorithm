package algorithm.practice.groom.recursion;

public class Prac02 {
    public static final char[] ITEMS = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        // 알파벳중에서 서로다른 N개의 문자를 뽑는 '조합의 수' 구하기
        // 알파벳의 순서는 고려하지 않는다, 즉 같은 알파벳을 사용한 조합은 모두 하나의 경우의 수로 생각한다.
        // 먼저 다중 반복문으로 구현해보기 위해 알파벳 중 3개를 뽑는걸 구현해보자
        int count = 0;

        for (int i = 0; i < 26; i++) {
            for (int j = i + 1; j < 26; j++) {
                for (int w = j + 1; w < 26; w++) {
                    System.out.printf("%c%c%c\n", ITEMS[i], ITEMS[j], ITEMS[w]);
                    count++;
                }
            }
        }

        System.out.println(count); // 26개중 3개 뽑는 조합의 수 출력

        // 순열과 달리 조합에서는
        // 형제 노드로 옮겨갈 때 이전 상태의 결정값은 재등장하지 못하게 해야한다 (중복 제거)
    }
}
