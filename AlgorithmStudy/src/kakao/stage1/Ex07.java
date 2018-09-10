package kakao.stage1;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Ex07 {
    public static void main(String[] args) {
        String[] lines = {
                "2016-09-15 20:59:57.421 0.351s", // 응답 완료시간과 처리시간
                "2016-09-15 20:59:58.233 1.181s",
                "2016-09-15 20:59:58.299 0.8s",
                "2016-09-15 20:59:58.688 1.041s",
                "2016-09-15 20:59:59.591 1.412s",
                "2016-09-15 21:00:00.464 1.466s",
                "2016-09-15 21:00:00.741 1.581s",
                "2016-09-15 21:00:00.748 2.31s",
                "2016-09-15 21:00:00.966 0.381s",
                "2016-09-15 21:00:02.066 2.62s"
        };

        /*String[] lines = {
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"
        };*/

        int result = new Ex07().solution(lines);
        System.out.println(result);
    }

    public int solution(String[] lines) {
        List<Log> list = new ArrayList<>();

        String token[];
        for (String line : lines) {
            token = line.split("\\s");
            LocalDate date = LocalDate.parse(token[0]);
            LocalTime time = LocalTime.parse(token[1]);
            LocalDateTime end = LocalDateTime.of(date, time);
            Duration t = Duration.parse("PT"+token[2]);
            t = t.minusMillis(1);
            LocalDateTime start = end.minus(t);
            list.add(new Log(start, end));
        }

        Collections.sort(list); // 시작 시간 기준으로 오름차순으로 정렬됨
        List<Integer> answers = new ArrayList<>();

        for (Log log : list) {
            LocalDateTime start = log.start;
            LocalDateTime end = log.end;

            answers.add(countLogsInRange(start, list));
//            answers.add(countLogsInRange(end.minus(1, ChronoUnit.MILLIS), list));
            answers.add(countLogsInRange(end, list));
        }

        Collections.sort(answers, Comparator.reverseOrder());
        int answer = answers.get(0);
        return answer;
    }

    private Integer countLogsInRange(LocalDateTime start, List<Log> list) { // 기준시간으로부터 1초동안 범위에 있는 아이들
        int cnt = 0;
        LocalDateTime end = start.plus(999, ChronoUnit.MILLIS);

        // 1초 범위내에 있는 로그들 검사
        for (Log log : list) {
            if (log.start.isBefore(start) && log.end.isBefore(start)) {
                continue;
            }
            if (log.start.isAfter(end)) {
                continue;
            }
            cnt++;
        }

        return cnt;
    }

    class Log implements Comparable<Log>{
        LocalDateTime start;
        LocalDateTime end;

        public Log(LocalDateTime start, LocalDateTime end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Log o) {
            return this.start.compareTo(o.start);
        }
    }
}
