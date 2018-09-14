package algorithm.practice.kakao.prac;


public class Solution02 {
    public static void main(String[] args) {
        // 정보를 잘 담자. 가장 중요.
        int a = new Solution02().solution("1S*2T*3S");
        System.out.println(a);
    }

    public int solution(String dartResult) {
        String[] chances = dartResult.split("(?<=[SDT])");
        int[] power = new int[3];
        int[] multi = new int[3];
        for (int i = 0; i < chances.length; i++) {
            if (i != 3) {
                multi[i] = 1;
            }

            if (chances[i].contains("*")) {
                multi[i - 1] = 2;
                chances[i] = chances[i].replace("*", " ").trim();
            }
            if (chances[i].contains("#")) {
                multi[i - 1] = -1;
                chances[i] = chances[i].replace("#", " ").trim();
            }

            if (chances[i].contains("S")) {
                power[i] = 1;
                chances[i] = chances[i].replace("S", " ").trim();
            }
            if (chances[i].contains("D")) {
                power[i] = 2;
                chances[i] = chances[i].replace("D", " ").trim();
            }
            if (chances[i].contains("T")) {
                power[i] = 3;
                chances[i] = chances[i].replace("T", " ").trim();
            }
        }
        // chances 배열에는 sdt로 나뉘었고 bonus 에는 0,1,2 인덱스에 *나 # 혹은 ""이 들어있다.

        // 점수계산
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            int num = Integer.parseInt(chances[i]);
            int firstRes = (int) Math.pow(num, power[i]);
            if (multi[i] == 2 && i != 0) {
                firstRes *= multi[i];
                result[i-1] *= multi[i];
            } else {
                firstRes *= multi[i];
            }
            result[i] = firstRes;
        }

        int answer = 0;
        for (int n : result) {
            answer += n;
        }

        return answer;
    }
}
