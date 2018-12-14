package algorithm.practice.groom;

import java.util.Scanner;

public class Goorm5B {
    public static final Scanner sc = new Scanner(System.in);
    static int[][] map = new int[300][300];

    public static void main(String[] args) throws Exception {
        int tc = sc.nextInt(); //1~100
        for(int i=0;i<tc;i++){
            testCase();
        }
    }

    private static void testCase(){
        int n = sc.nextInt(); //공터크기 1~300
        int k = sc.nextInt(); //구매할 땅 1~300

        // 단순 반복문으로는 시간복잡도 O(N^2 * K^2) : 9만 * 9만 -> 81억번

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                map[i][j] = sc.nextInt();
            }
        }

        int minCnt = getMinWaste(n, k);
        System.out.println(minCnt);
    }

    private static int getMinWaste(int n, int k){
        int minWaste = k * k;

        for(int r=0; r+k-1<n; r++){ // 높이 k 만큼의 칼럼만 탐색해서 시간복잡도를 줄였음 O(N^2 * K) : 최대 1000만번

            int count = 0;
            for(int c=0; c<n; c++){
                for(int dr=0; dr<k; dr++){
                    count += map[r+dr][c];
                }
                if(c>=k){
                    for(int dr=0; dr<k; dr++){
                        count -= map[r+dr][c-k];
                    }
                }
                if(c >= k-1){
                    if(minWaste > count)
                        minWaste = count;
                }
            }
        }

        return minWaste;
    }
}
