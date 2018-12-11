package algorithm.practice.groom;

import java.util.Scanner;

public class Groom2J {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        // 탐색해야하는 범위들과 그 범위간의 관계를 이용하면
        // 단순 반복문으로 해결할 수 있다
        // 인덱스 0부터 인접한 k만큼을 검사해서 그 합이 짝수인지를 반복문으로 확인하면
        // n-k 만큼 연산이 되고 그 안에서 항상 k만큼 sum에 더해지니깐
        // 총 O(nk-k^2) 만큼의 시간이 걸린다.

        // 그럼 탐색해야하는 범위는 어디인가
        // k가 3일때 인덱스 0,1,2 를 더하고 짝수면 종료
        // 홀수면 인덱스 3을 더하고 인덱스 0을 뺀 수가 짝수면 종료
        // 홀수면 인덱스 4를 더하고 인덱스 1을 뺀 수가 짝수면 종료
        // sliding window 라는 아이디어임

        int[] arr = new int[100000];
        int n = sc.nextInt(); // 총 종이컵 수 1~10만
        int k = sc.nextInt(); //선택할 종이컵 수 1~10만

        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();//0~100만
        }

        if(isAnyEven(arr, n, k))
            System.out.println("YES");
        else
            System.out.println("NO");
    }

    private static boolean isAnyEven(int[] arr, int n, int k){
        int sum = 0;

        for(int i=0;i<k;i++){
            sum += arr[i];
        }

        if(sum % 2 == 0)
            return true;

        for(int i=k;i<n;i++){
            sum += arr[i];
            sum -= arr[i-k];
            if(sum % 2 == 0){
                return true;
            }
        }

        return false;
    }
}
