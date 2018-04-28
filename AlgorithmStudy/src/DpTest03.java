import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DpTest03 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testcase = Integer.parseInt(br.readLine());
        while (testcase-- > 0){
            int n = Integer.parseInt(br.readLine());
            int[] zeroCnt = new int[n+1];
            int[] oneCnt = new int[n+1];
            zeroCnt[0] = 1;
            oneCnt[0] = 0;
            if(n>0){
                zeroCnt[1] = 0;
                oneCnt[1] = 1;
            }

            for(int i=2;i<=n;i++){
                zeroCnt[i] = zeroCnt[i-1] + zeroCnt[i-2];
                oneCnt[i] = oneCnt[i-1] + oneCnt[i-2];
            }
            System.out.println(zeroCnt[n]+" "+oneCnt[n]);
        }
    }
}
