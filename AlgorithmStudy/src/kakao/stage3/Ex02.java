package kakao.stage3;

import java.util.*;

public class Ex02 {
    public static void main(String[] args) {
        String msg = "TOBEORNOTTOBEORTOBEORNOT";
        int[] answer = new Ex02().solution(msg);
        System.out.println(Arrays.toString(answer));
    }

    public int[] solution(String msg) {
        int nextIndex = 27;
        int nextStartIndex = 0; // msg.length - 1 되면 끝내게

        Map<String, Integer> dictionary = new HashMap<>();
        List<Integer> list = new ArrayList<>();

        while (true) {
            if (nextStartIndex > msg.length() - 1) { // msg 의 인덱스를 초과했다면 끝
                break;
            }
            if (nextStartIndex == msg.length() - 1) { // 마지막 1글자 남았다면 해당 인덱스 추가 후 끝
                char w = msg.charAt(nextStartIndex);
                list.add(w - 64);
                break;
            }

            String toFind = msg.substring(nextStartIndex, nextStartIndex + 2);
            String w = "";
            if (dictionary.containsKey(toFind)) {
                w = toFind;
                boolean findLargerW = true;
                int i = 2;
                while (findLargerW) {
                    i++;
                    if (nextStartIndex + i > msg.length()) {
                        break;
                    }
                    toFind = msg.substring(nextStartIndex, nextStartIndex + i);
                    if (dictionary.containsKey(toFind)) {
                        findLargerW = true;
                        w = toFind;
                    } else {
                        findLargerW = false;
                    }
                }
                // w 는 사전에서 찾아서 인덱스 저장시키고 toFind 는 사전에 저장
                list.add(dictionary.get(w));
                dictionary.put(toFind, nextIndex++);
                nextStartIndex += w.length();

            } else {
                char c = msg.charAt(nextStartIndex);
                list.add(c - 64);
                dictionary.put(toFind, nextIndex++);
                nextStartIndex++;
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
