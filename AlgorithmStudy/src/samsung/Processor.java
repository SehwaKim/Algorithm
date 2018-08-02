package samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 70% 정도의 확신을 갖은 설계 후 작성한 코드 - 실패함

public class Processor {
    static List<String[][]> cases;
    static List<List<Integer>> cores;
    static List<List<Integer>> sums;
    static boolean[][] visited = new boolean[12][12];
    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};
    static int current;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testCase = parseInt(br.readLine().trim());
        cases = new ArrayList<>(testCase);
        cores = new ArrayList<>(testCase);
        sums = new ArrayList<>(testCase);

        for (int i = 0; i < testCase; i++) {
            int mapSize = parseInt(br.readLine().trim());
            cases.add(new String[mapSize][mapSize]);
            cores.add(new ArrayList<>());
            sums.add(new ArrayList<>());
            String[][] map = cases.get(i);
            for (int j = 0; j < mapSize; j++) {
                map[j] = br.readLine().trim().split("\\s");
            }
        }

        // 요구사항: 최대한 많은 core 연결하라
        // connect(): 코어를 전원에 4방향으로 연결
        for (int i = 0; i < testCase; i++) {
            String[][] map = cases.get(i);
            solveByCase(map);
            List<Integer> connected = cores.get(current);
            List<Integer> sum = sums.get(current);
            int maxNum = 0;
            int index = 0;
            for (int j = 0; j < connected.size(); j++) {
                if (j == 0) {
                    maxNum = connected.get(j);
                    continue;
                }
                if (maxNum < connected.get(j)) {
                    maxNum = connected.get(j);
                    index = j;
                }
            }
            System.out.println("#" + (i + 1) + " " + sum.get(index));
            current++;
        }
    }

    private static void solveByCase(String[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (i == 0 || i == map.length - 1 || j == 0 || j == map.length - 1) { // 벽면에 붙어있는 코어들은 무시한다
                    continue;
                }
                if (map[i][j].equals("1")) { // core 를 만나다!
                    int sum = 0;
                    connect(new int[]{i, j}, sum, 0, map); // 코어좌표, 전선길이합, 연결된 코어갯수
                }
            }
        }
    }

    private static void connect(int[] vertex, int sum, int connectedCores, String[][] map) {
        // 상 하 좌 우 성공시 또다른 connect() 호출
        int y = vertex[0];
        int x = vertex[1];
        for (int i = 0; i < 4; i++) {
            boolean success = false;
            int ny = y;
            int nx = x;
            int tmp = sum;
            while (true) {
                ny += dy[i];
                nx += dx[i];
                if (!map[ny][nx].equals("0") || visited[ny][nx]) { // 전선을 놓을 수 없으면 실패한채로 빠져나옴
                    // visited 되돌리기
                    for (int w = 1; w < Math.max(Math.abs(ny - y), Math.abs(nx - x)); w++) {
                        visited[ny - w][nx - w] = false;
                    }
                    break;
                }

                tmp++;
                visited[ny][nx] = true;

                if (ny == 0 || ny == map.length - 1 || nx == 0 || nx == map.length - 1) { // 벽면을 만나면 전원 연결 성공
                    success = true;
                    map[y][x] = "2"; // 이 코어는 연결됐음을 표시
                    break;
                }
            }
            if (success) { // 전선 깔기 성공시 또 다른 코어에 대해 connect() 호출
                sum = tmp;
                connectedCores++;

                boolean finished = true;
                for (int j = 0; i <= map.length; i++) {
                    for (int w = 0; w <= map.length; w++) {
                        if (j == 0 || w == 0) { // 벽면에 붙어있는 코어들은 무시한다
                            continue;
                        }
                        if (map[j][w].equals("1")) {
                            finished = false;
                            connect(new int[]{j, w}, sum, connectedCores, map);
                            map[y][x] = "1"; // 이 코어 연결표시 원복
                        }
                    }
                }
                if (finished) {
                    cores.get(current).add(connectedCores);
                    sums.get(current).add(sum);
                }
            }
        }
    }

    private static int parseInt(String s) {
        return Integer.parseInt(s);
    }
}
