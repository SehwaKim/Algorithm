package dp;

public class Fibonacci02 {
    static int[] dp;

    public static void main(String[] args) {
        // bottom-up 방식
        // 점화식
        // fib(n) = fib(n-1)+fib(n-2)
        // base case: n=1,n=2 ---> fib(n)=1
        dp = new int[11];
        for (int i = 1; i < dp.length; i++) {
            System.out.println(fib(i));
        }
    }

    private static int fib(int i) {
        // 점화식의 오른쪽이 먼저 계산되서 최종 결과로 수렴하는 형태
        // base case 부터 세팅
        dp[1] = dp[2] = 1;
        // 그다음은 3번째 항부터 i 항 까지 답을 만들어나간다
        for (int j = 3; j <= i; j++) {
            dp[j] = dp[j - 1] + dp[j - 2];
        }
        return dp[i];
    }
}
