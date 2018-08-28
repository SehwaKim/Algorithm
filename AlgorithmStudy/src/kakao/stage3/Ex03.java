package kakao.stage3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex03 {
    public static void main(String[] args) {
        String[] files = {"img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] answer = new Ex03().solution(files);
        System.out.println(Arrays.toString(answer));
    }

    public String[] solution(String[] files) {
        List<FileName> fileNameList = new ArrayList<>();
        String head, number, tail;

        for (int i = 0; i < files.length; i++) {
            String s = files[i];
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(s);
            if (m.find()){
                number = m.group();
                int numberStartIndex = s.indexOf(number);
                head = s.substring(0, numberStartIndex);
                int numberEndIndex = numberStartIndex + number.length() - 1;
                if (numberEndIndex + 1 > s.length() - 1) {
                    tail = "";
                } else {
                    tail = s.substring(numberEndIndex + 1);
                }
                fileNameList.add(new FileName(head, number, tail));
            }
        } // 세부분 나누기 끝

        fileNameList.sort(Comparator.comparing(FileName::getHead).thenComparing(Comparator.naturalOrder()));

        String[] answer = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            answer[i] = fileNameList.get(i).toString();
        }
        return answer;
    }

    public static class FileName implements Comparable<FileName> {
        String head;
        String number;
        String tail;

        public FileName(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }

        public String getHead() {
            return head.toUpperCase();
        }

        @Override
        public String toString() {
            return head + number + tail;
        }

        @Override
        public int compareTo(FileName o) {
            int mine = Integer.parseInt(this.number);
            int yours = Integer.parseInt(o.number);
            return mine - yours;
        }
    }
}
