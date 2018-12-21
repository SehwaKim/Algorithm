package algorithm.practice.groom.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Goorm7C {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int n = sc.nextInt(); // 지도의 크기 1~25
        int[][] map = new int[n + 2][n + 2];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 문제의 정의를 함수로 옮기는 연습이 중요하다
        List<Integer> groups = getAllGroupSize(map, n); // 지도에 존재하는 총 단지의 수 k를 출력하기
        System.out.println(groups.size());
        // 그 후 총 k 줄에 걸쳐 각 단지의 집의 수 출력하기
        for (Integer houses : groups) {
            System.out.println(houses);
        }

        // 이 문제는 지도를 탐색하고 집의 갯수를 세는거다
        // 어떻게 부분 문제로 나뉠 수 있을까? 작은 크기의 문제로 만들 수 있을까?
        // blah blah 생각의 흐름을 거쳐
        // 아하! (r 행 c 열)의 집으로부터 연결된 아직 방문되지 않은 집을 총 수를 리턴하는 함수를 만들자
        // 라고 재귀함수를 설계할 수 있을까.
        // 재귀함수를 설계한다는 말은 뭘까? 전체 문제를 정확히 부분 문제로 나누는 것 그 자체일거다.
    }

    private static List<Integer> getAllGroupSize(int[][] map, int n) {
        List<Integer> groups = new ArrayList<>();
        // 리턴해야할 것은 각 단지의 집의 갯수들이 저장된 리스트이다.
        // 각 단지의 집의 갯수를 세어야 한다. --> 지도를 탐색하여 집인지 아닌지 검사한다 --> 한 칸을 검사하는 것 자체가 하나의 상태이다

        boolean[][] visited = new boolean[n + 2][n + 2];

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                // if(map[r][c] == 1) { count++; }
                // (r,c) 가 집인지 단순히 세는 게 아니라, 인접된 집들끼리는 그룹으로 쳐야한다.
                // (r,c)이 집인지 아닌지 검사하고 4방향으로 다시 탐색을 시키는게 부분문제이다
                // 여기서 구할것은 (r,c) 포함해서 연결된 모든 집의 갯수를 세는거다
                // int count = (r,c) 포함 + (r,c)와 연결된 모든 집의 갯수

                // (r,c)와 연결된 집들이 총 몇갠지 리턴하는 함수를 정의했다

                if (map[r][c] == 1 && !visited[r][c]) {
                    int count = getLinkedHouses(r, c, map, visited, n);
                    groups.add(count);
                }
            }
        }

        return groups;
    }

    // (r,c)와 연결된 집들이 총 몇갠지 리턴하는 함수
    private static int getLinkedHouses(int r, int c, int[][] map, boolean[][] visited, int n) {
        if (visited[r][c] || map[r][c] == 0) {
            return 0;
        }

        if (r < 1 || c < 1 || r > n || c > n) {
            return 0;
        }

        visited[r][c] = true;
        int count = 1;

        int[] dr = {0, 0, -1, 1};
        int[] dc = {1, -1, 0, 0};

        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            count += getLinkedHouses(nr, nc, map, visited, n);
        }

        return count;
    }
}

