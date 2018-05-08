package kakao.stage1;

import java.util.ArrayList;
import java.util.Collections;

// http://tech.kakao.com/2017/09/27/kakao-blind-recruitment-round-1/
// 7. 추석 트래픽(난이도: 상)
public class Quiz07 {



    public static void main(String[] args) {

        solution(new String[]{"2016-09-15 03:10:33.020 0.011s"});

        solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s"});

        solution(new String[]{"2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s"});

        solution(new String[]{"2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"});

        solution(new String[]{"2016-09-15 01:00:04.001 2.0s", "2016-09-15 01:00:07.000 2s", "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s", "2016-09-15 20:59:57.421 0.351s", "2016-09-15 20:59:58.233 1.181s", "2016-09-15 20:59:58.299 0.8s", "2016-09-15 20:59:58.688 1.041s", "2016-09-15 20:59:59.591 1.412s", "2016-09-15 21:00:00.464 1.466s", "2016-09-15 21:00:00.741 1.581s", "2016-09-15 21:00:00.748 2.31s", "2016-09-15 21:00:00.966 0.381s", "2016-09-15 21:00:02.066 2.62s"});

    }



    static void solution(String[] inputLogs) {

        ArrayList<log> logs = getLogs(inputLogs);

        if ( logs.size() == 0 ) {
            System.out.println(0);
            return;
        } else if ( logs.size() == 1 ) {
            System.out.println(1);
            return;
        }

        int result = 1;

        Collections.sort(logs);	//startTime을 기준으로 정렬

        for ( int i = 0; i < inputLogs.length-1; i++ ) {

            int num = 1;	//자기 자신을 포함해서 1

            int exitTime = logs.get(i).getExitTime();

            for ( int j = i+1; j < inputLogs.length; j++ ) {
                int tempStartTime = logs.get(j).getStartTime();
                int tempExitTime = logs.get(j).getExitTime();
                if ( tempStartTime <= exitTime + 999 && exitTime <= tempExitTime ) {
                    num++;
                }
            }

            result = result < num ? num : result;
        }

        System.out.println(result);

    }



    static ArrayList<log> getLogs(String[] inputLogs) {

        ArrayList<log> logs = new ArrayList<log>();

        for ( int i = 0; i < inputLogs.length; i++ ) {
            logs.add(new log(inputLogs[i]));
        }

        return logs;

    }

}



class log implements Comparable<log> {



    private int startTime, exitTime;



    public log(String time) {



        int exitTime = 0;

        String[] timeSplit = time.split(" ");

        String[] exitTimeSplit = timeSplit[1].split(":");



        exitTime += Integer.valueOf(exitTimeSplit[0]) * 3600000;

        exitTime += Integer.valueOf(exitTimeSplit[1]) * 60000;



        exitTime += getSeconds(exitTimeSplit[2]);

        this.exitTime = exitTime;

        this.startTime = exitTime - getSeconds(timeSplit[2].replace("s", "")) + 1;

    }



    int getSeconds(String second) {

        int time = 0;

        String[] Seconds = second.split("\\.");

        time += Integer.valueOf(Seconds[0]) * 1000;

        if ( Seconds.length > 1 ) {

            int temp = Integer.valueOf(Seconds[1]);

            if ( Seconds[1].length() == 1 ) {
                temp *= 100;
            } else if ( Seconds[1].length() == 2 ) {
                temp *= 10;
            }

            time += temp;

        }

        return time;

    }



    public int getStartTime() {

        return startTime;

    }



    public int getExitTime() {

        return exitTime;

    }



    @Override	//startTime을 기준으로 정렬하기

    public int compareTo(log otherLog) {

        if (this.startTime < otherLog.getStartTime()) {

            return -1;

        } else if (this.startTime > otherLog.getStartTime()) {

            return 1;

        }

        return 0;

    }

}

