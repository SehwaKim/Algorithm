package algorithm.practice.groom.recursion;

public class Prac01 {
    public static void main(String[] args) {
        // a~z 에서 3글자를 사용해서 만들수 있는 단어를 모두 출력하기
        // 순열

        int count = 0;

        for (int i = 0; i < 26; i++) {
            // 첫번째 글자 A~Z
            for (int j = 0; j < 26; j++) {
                // 두번째 글자 A~Z
                for (int w = 0; w < 26; w++) {
                    // 세번째 글자 A~Z
                    System.out.printf("%c%c%c\n", 'A' + i, 'A' + j, 'A' + w);
                    count++;
                }
            }
        }

        System.out.println(count); // 26개 중 3개 뽑은 순열의 수 출력
    }
}
