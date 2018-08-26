package kakao.stage1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
3
[“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”, “Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”]
50

3
[“Jeju”, “Pangyo”, “Seoul”, “Jeju”, “Pangyo”, “Seoul”, “Jeju”, “Pangyo”, “Seoul”]
21

2
[“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”, “SanFrancisco”, “Seoul”, “Rome”, “Paris”, “Jeju”, “NewYork”, “Rome”]
60

5
[“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”, “SanFrancisco”, “Seoul”, “Rome”, “Paris”, “Jeju”, “NewYork”, “Rome”]
52

2
[“Jeju”, “Pangyo”, “NewYork”, “newyork”]
16

0
[“Jeju”, “Pangyo”, “Seoul”, “NewYork”, “LA”]
25
* */
public class Ex03 {
    // 캐시 - 하
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cacheSize = parseInt(br.readLine());
        String[] cities = br.readLine().split(", ");
        cities[0] = cities[0].substring(1);
        cities[cities.length - 1] = cities[cities.length - 1].substring(0, cities[cities.length - 1].length() - 1);

        int time = 0;

        if (cacheSize == 0) {
            System.out.println(cities.length * 5);
            return;
        }

        // 가장 오랜된걸 버리기위해 큐를 사용하자..
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < cities.length; i++) {
            // 캐시에 있는지 확인부터 - 있으면 큐의 맨앞으로 옮겨주기
            boolean hit = ((LinkedList<String>) queue).removeFirstOccurrence(cities[i].toUpperCase());
            queue.add(cities[i].toUpperCase()); // 큐에 add 하면 last 에 추가되는
            time += 1;
            if (!hit) { // miss 일 때
                time += 4;
                // 큐 사이즈 벗어나면 오래된 것 버리기
                if (queue.size() > cacheSize) {
                    String removed = ((LinkedList<String>) queue).removeFirst();
                }
            }
        }

        System.out.println(time);
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
