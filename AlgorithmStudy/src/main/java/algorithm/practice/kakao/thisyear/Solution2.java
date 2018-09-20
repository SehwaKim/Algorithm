package algorithm.practice.kakao.thisyear;

import java.util.*;

public class Solution2 {
    public static void main(String[] args) {
//        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3}; // 3,4,2,1,5 나와야 함
//        int N = 5;

        int[] stages = {4, 4, 4, 4, 4};
        int N = 4;
        int[] a = new Solution2().solution(N, stages);
        System.out.println(Arrays.toString(a));
    }

    public int[] solution(int N, int[] stages) {
        Map<Integer, Integer> playersByStage = new HashMap<>();

        for (int i = 0; i < stages.length; i++) {
            int stage = stages[i];
            playersByStage.compute(stage, (k, v) -> {
                if (v == null) {
                    v = 0;
                }
                return v + 1;
            });
        }

        // 총 플레이어 수
        int totPlayer = stages.length;

        // 확인
//        for (Integer k : playersByStage.keySet()) {
//            System.out.println(k+": "+playersByStage.get(k)+"명");
//        }

        // stage 별 실패율 담을 리스트 생성
        List<String> rateByStage = new ArrayList<>();
        for (int i = 1; i <= N; i++) { // 각 스테이지 별로 실패율 구해서 넣기
            // 분모
            int tot = totPlayer;
            for (int j = i - 1; j >= 1; j--) { // stage 가 4일때, 1~3 제외하고 모든 선수들 j=3,2,1
                int pCnt = playersByStage.getOrDefault(j, 0);
                tot -= pCnt;
            }

            // 분자
            int onTheStageCnt = playersByStage.getOrDefault(i, 0);

            double rate;
            if (tot == 0) {
                rate = 0;
            } else {
                rate = (double) onTheStageCnt / tot;
            }

            rateByStage.add(i + "|" + rate);
        }

        // list 정렬 후 스테이지만 인트로 뽑아서 int[] 리턴하기
        Comparator<String> c = (s1, s2)->{
            String[] token = s1.split("\\|");
            Double rate1 = Double.parseDouble(token[1]);
            token = s2.split("\\|");
            Double rate2 = Double.parseDouble(token[1]);
            double gap = rate2 - rate1;

            return gap == 0 ? 0 : (gap < 0 ? -1 : 1);
        };
        Collections.sort(rateByStage, c.thenComparing((s1,s2)->{
            String[] token = s1.split("\\|");
            int stage1 = Integer.parseInt(token[0]);
            token = s2.split("\\|");
            int stage2 = Integer.parseInt(token[0]);

            return stage1 - stage2;
        }));

        int[] answer = new int[rateByStage.size()];
        for (int i = 0; i < rateByStage.size(); i++) {
            String[] token = rateByStage.get(i).split("\\|");
            int stage = Integer.parseInt(token[0]);
            answer[i] = stage;
        }
        return answer;
    }
}
