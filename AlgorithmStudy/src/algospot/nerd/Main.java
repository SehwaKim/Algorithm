package algospot.nerd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) throws Exception {
//        Scanner scanner = new Scanner(System.in);
//        int testcase = scanner.nextInt();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = parseInt(br.readLine());
        for(int testIndex = 0; testIndex < testcase; testIndex++) {
            int nodeNum = parseInt(br.readLine()); // 참가 신청한 사람 수
            TreeMap map = new TreeMap(); // 레드블랙트리에 기반한 NavigableMap 의 구현체라고 함자
            // 이 맵은 다른 점에 지배당하지 않는 점들의 목록을 저장하기 위한 트리.

            int sum = 0; // 최종답 - 대회 참가 가능 인원 수
            for(int nodeIndex = 0; nodeIndex < nodeNum; nodeIndex++) {
                String[] token = br.readLine().split("\\s");
                int x = parseInt(token[0]); // 문제 푼 수
                int y = parseInt(token[1]); // 라면 그릇 수
                Entry entry=map.ceilingEntry(x); // x보다 큰 키 중에 가장 작은 키, 바로 오른 쪽 키의 Entry 를 리턴 (혹은 똑같은 키값)
                // NavigableMap 인터페이스에 있는 메소드군

                // 지배당하는지 봤는데 오른쪽에 아예 키가 없으면 지배당하지 않는 케이스 이므로 그냥 집어넣고 사이즈를 파악.
                if(entry == null) {
                    map.put(x, y);
                    delete(map, x, y); // 새로운 노드 들어왔으므로 얘한테 지배되는 애들 다 삭제
                    sum += map.size(); // 지배당하지 않는 자 사이즈. 왜 대입이 아니라 += 일까?
                    continue;
                }

                // entry 가 null 이 아니라면 x랑 같거나 바로 오른쪽 키
                if ((int) entry.getValue() > y) { // 새로들어온 점보다 높이 떠 있으면 지배하는 것
                    sum += map.size();
                    continue;
                } else { // 높이가 같거나 작으면 새로 영입
                    map.put(x, y);
                    delete(map, x, y); //왼쪽에 있는 키를 삭제하는 연산
                    sum += map.size();
                    continue;
                }
            }
            System.out.println(sum);
        }
    }
    //왼쪽에 있는 키를 제거한다. 새멤버의 y(int value) 보다 큰 value 를 가진 key 가 나타날 때 까지.
    private static void delete(TreeMap<Integer, Integer> map, int key, int value) {
        SortedMap<Integer, Integer> smap = map.headMap(key);
        // headMap(toKey) -> Returns a view of the portion of this map whose keys are strictly less than toKey
        // 참고: HashMap 은 order 를 보장하지 못한다고 함

        Object[] arr= smap.keySet().toArray();

        for(int i = arr.length - 1; i >=0; i--) {
            int tempkey = (int) arr[i];
            int tempValue = map.get(tempkey);
            if(tempValue < value) {
                map.remove(tempkey);
            } else {
                break;
            }
        }
    }
}