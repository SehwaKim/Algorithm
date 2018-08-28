package kakao.stage3;

public class Ex01 {
    public static void main(String[] args) {
//        String answer = new Ex01().solution(16, 16, 2, 1);
        String answer = new Ex01().solution(2, 4, 2, 1);

        System.out.println(answer);
    }

    public String solution(int n, int t, int m, int p) {
        // n 진법, t 출력갯수, m 인원, p 튜브의 순서

        int decimal = 0;
        int nextTube = p;
        int allocated = 0; // 어디까지 배당됐나
        StringBuilder sb = new StringBuilder();

        loop: while (true) {
            String convertedNum = convert(decimal++, n);
            for (int j = 0; j < convertedNum.length(); j++) {
                allocated++;
                if (allocated == nextTube) {
                    nextTube += m;
                    sb.append(convertedNum.charAt(j));
                    if (sb.length() == t) {
                        break loop;
                    }
                }
            }
        }

        String answer = sb.toString();
        return answer;
    }

    private String convert(int decimal, int n) {
        StringBuilder converted = new StringBuilder();
        while (true) {
            int carry = decimal % n;
            if (carry >= 10) {
                carry = 'A' + carry - 10;
                char c = (char) carry;
                converted.append(c);
            } else {
                converted.append(carry);
            }
            decimal /= n;
            if (decimal == 0) {
                break;
            }
        }
        return converted.reverse().toString();
    }
}
