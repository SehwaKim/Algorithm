package algorithm.practice.groom;

import java.util.Scanner;

public class Goorm5C {
    public static final Scanner sc = new Scanner(System.in);

    static int[][] map = new int[101][101];
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args){
        int tc = sc.nextInt();
        for(int i=0;i<tc;i++){
            testCase();
        }
    }

    private static void testCase(){
        int n = sc.nextInt(); //1~100 지도
        int k = sc.nextInt(); //1~100 후보 건설 지점 수

        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                map[i][j] = sc.nextInt(); // 1 - 특수시설 존재
            }
        }

        int max = 0;
        // 가장 많은 특수시설(지도상에서 1) 보호할 후보지점 찾기
        for(int i=0;i<k;i++){
            int r = sc.nextInt();
            int c = sc.nextInt();

            // 8 방향을 모두 검색해야한다
            int cnt = map[r][c];
            cnt += searchAll(r, c, n);

            if(max < cnt)
                max = cnt;
        }

        System.out.println(max);
    }

    private static int searchAll(int r, int c, int n){
        int count = 0;

        for(int i=0;i<8;i++){
            for(int step = 1; step <=n; step++){
                int nr = r + step*dr[i];
                int nc = c + step*dc[i];
                if(nr < 1 || nr > n || nc < 1 || nc > n){
                    break;
                }
                count += map[nr][nc];
            }
        }

        // 시간복잡도는 후보수 K에 대해서 8방향으로 최대 N번(끝에서 끝) 도니까
        // O(K * 8 * N) : 100 * 8 * 100 = 8만번

        return count;
    }
}