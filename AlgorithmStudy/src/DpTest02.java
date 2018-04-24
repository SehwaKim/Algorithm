import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DpTest02 {
    public static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase=0;
        int N=0;

        try {
            testcase = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(testcase-->0){
            try {
                N = Integer.parseInt(br.readLine());

            } catch (IOException e) {
                e.printStackTrace();
            }

            dp = new int[11];

            dp[0] = 1;

            for(int i = 1; i<=N; i++){
                if(i-1 >= 0){
                    dp[i] += dp[i-1];
                }
                if(i-2 >= 0){
                    dp[i] += dp[i-2];
                }
                if(i-3 >= 0){
                    dp[i] += dp[i-3];
                }
            }

            System.out.println(dp[N]);
        }
        br.close();
    }
}
