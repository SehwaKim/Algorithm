package dp;

public class Fibonacci {
    static int[] dp;

    public static void main(String[] args) {
        dp = new int[11];
        for (int i = 1; i < 11; i++) {
            System.out.println(i+"번째 피보나치: " + fib(i));
        }
    }

    private static int fib(int n) {
        // 점화식
        // fib(n) = fib(n-1)+fib(n-2)
        // base case: n=1,n=2 ---> fib(n)=1

        if (n == 1 || n == 2) {
            return 1;
        }
        if (dp[n] == 0) {
            dp[n] = fib(n-1)+fib(n-2);
        }
        return dp[n];
    }
}
