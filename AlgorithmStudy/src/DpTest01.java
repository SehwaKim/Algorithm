import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 백준 1463번
* 내가 관찰한 것을 코드로 구현하는 것이 중요하다...
*
* */
public class DpTest01 {
    public static int[] dp;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N=0;

        try {
            N = Integer.parseInt(br.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 각 N에 대한 N->1 최소 연산 횟수를 저장해야 한다
        // 저장하기 위한 배열 선언, N으로 초기화
        dp = new int[N+1]; //N이 5이면 인덱스 0~5가 되게끔. dp[5]를 구해야 하니까.

        // N이 1일 때는 /3, /2, -1 어느것도 불가능하니까 연산 횟수 0임
        dp[1] = 0;

        // 2일 때부터 최소 연산횟수 구해서 배열에 저장해야 한다
        // 2일 때, 3일 때, ... 마지막으로 N일 때 까지
        int count;
        for(int i = 2; i<=N; i++){
            if(i%3==0){
                dp[i] = dp[i/3] + 1;
            }
            if(i%2==0){
                count = dp[i/2] + 1;
                if(dp[i] == 0 || count < dp[i]){
                    dp[i] = count;
                }
            }
            if(i-1 != 0){
                count = dp[i-1] + 1;
                if(dp[i] == 0 || count < dp[i]){
                    dp[i] = count;
                }
            }
            //System.out.println(i+"->1 최소 연산 횟수 : "+dp[i]);
        }

        // N일때 최소연산횟수 출력
        System.out.println(dp[N]);

    }
}
