package kakao.stage1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
1S2D*3T     37
1D2S#10S    9
1D2S0T      3
1S*2T*3S	23
1D#2S*3S	5
1T2D3D#	    -4
1D2S3T*	    59
* */

// 다트게임 - 하
public class Ex02 {
    public enum Bonus {
        S(1), D(2), T(3);

        int val;

        Bonus(int val) {
            this.val = val;
        }

        int getVal() {
            return val;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] set = input.split("(?<=[SDT])");

        // 자료에 이름을 붙여서 잘 저장해놓자
        int[] score = new int[3];
        char[] bonus = new char[3];
        char[] option = new char[3];

        for (int i = 0; i < set.length; i++) {
            if (i >= 3) {
                option[2] = set[i].charAt(0); // 마지막 남은 * 혹은 # 는 바로 넣는다
                continue;
            }
            String toProcess = set[i];
            bonus[i] = toProcess.charAt(toProcess.length() - 1); // 마지막 문자는 항상 bonus 이므로
            if (toProcess.charAt(0) == '*' || toProcess.charAt(0) == '#') {
                option[i - 1] = toProcess.charAt(0);
                score[i] = parseInt(toProcess.substring(1, toProcess.length() - 1));
            } else { // 첫 문자가 옵션이 아니라면 점수겠지
                score[i] = parseInt(toProcess.substring(0, toProcess.length() - 1));
            }
        }

        // 제곱승 처리
        int[] tempVal = new int[3];
        for (int i = 0; i < 3; i++) {
            int pow = Bonus.valueOf(bonus[i]+"").val;
            tempVal[i] = (int) Math.pow(score[i], pow);
        }

        // 옵션 처리
        for (int i = 0; i < 3; i++) {
            if (option[i] != '*' && option[i] != '#') {
                continue;
            }
            if (option[i] == '*') {
                if (i == 0) {
                    tempVal[0] *= 2;
                    continue;
                }
                tempVal[i] *= 2;
                tempVal[i - 1] *= 2;
            }
            if (option[i] == '#') {
                tempVal[i] *= -1;
            }
        }

        // 최종 합계
        System.out.println(tempVal[0] + tempVal[1] + tempVal[2]);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
