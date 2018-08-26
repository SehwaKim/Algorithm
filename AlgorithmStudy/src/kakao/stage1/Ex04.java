package kakao.stage1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Ex04 {
/*
1
1
5
[“08:00”, “08:01”, “08:02”, “08:03”]
9:00

2
10
2
[“09:10”, “09:09”, “08:00”]
09:09

2
1
2
[“09:00”, “09:00”, “09:00”, “09:00”]
08:59

1
1
5
[“00:01”, “00:01”, “00:01”, “00:01”, “00:01”]
00:00

1
1
1
[“23:59”]
09:00

10
60
45
[“23:59”,”23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”, “23:59”]
18:00
* */
    // 셔틀버스 - 중
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = parseInt(br.readLine()); // 운행 횟수
        int t = parseInt(br.readLine()); // 운행 간격 (분단위)
        int m = parseInt(br.readLine()); // 최대 탑승 인원
        String[] timetable = br.readLine().split(", ");
        timetable[0] = timetable[0].substring(1);
        timetable[timetable.length - 1] = timetable[timetable.length - 1].substring(0, timetable[timetable.length - 1].length() - 1);

        List<Integer> timetableToInt = new ArrayList<>(timetable.length); // HH:MM 배열을 분단위 리스트로 변환
        for (int i = 0; i < timetable.length; i++) {
            int hours = parseInt(timetable[i].substring(1, 3));
            int minutes = parseInt(timetable[i].substring(4, 6));
            timetableToInt.add(hours * 60 + minutes);
        }

        int first = 540; // 9:00 를 분으로 변환
        int last = first + (n - 1) * t;
        int conPassTime = 0;

        // 차례대로 크루들 태우기, 막차에 해당될 때 까지.
        // 크루들을 큐에 담아서 소진시키자.
        Collections.sort(timetableToInt);
        Queue<Integer> crewQueue = new LinkedList<>(timetableToInt); // first 부터 빼야 한다
        for (int i = 0; i < n; i++) { // 셔틀 운행횟수 n 만큼 도는 loop
            int validTime = first + i * t; // 현재 탑승할 차시간
            int onBoardCnt = 0; // 차에 탄 인원 세기 (최대 m명)
            int lastBoardTime = 0;
            for (int j = 0; j < m; j++) { // 최대 탑승 인원 m 만큼 도는 loop
                if (crewQueue.isEmpty()) {
                    break;
                }
                int targetCrewTime = ((LinkedList<Integer>) crewQueue).peekFirst();
                if (targetCrewTime <= validTime) {
                    lastBoardTime = ((LinkedList<Integer>) crewQueue).removeFirst(); // 차에 태워보냈음. 소진시킴.
                    onBoardCnt++;
                }
            }

            // 막차일 때
            if (i == n - 1) {
                if (onBoardCnt == m) { // 다 타서 경쟁해야 할 경우
                    conPassTime = lastBoardTime - 1;
                }
                if (onBoardCnt < m) { // 다 못타거나 남은 자리 있는 경우
                    conPassTime = last;
                }
            }
        }

        printTime(conPassTime);
    }

    private static void printTime(int conPassTime) {
        int hours = conPassTime / 60;
        int minutes = conPassTime % 60;
        System.out.printf("%d:%02d", hours, minutes);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
