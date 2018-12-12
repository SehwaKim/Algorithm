package algorithm.practice.groom;

import java.util.Scanner;

public class Groom3F {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        // 비슷한 계산을 반복해야 할 때 배열을 통해 간소화하는 방법을 고민하라

        int[] number = new int[100001];
        int n = sc.nextInt(); // 1~10만 숫자카드 수
        int m = sc.nextInt(); // 1~10만 구매한 팬 수

        for(int i=1;i<=n;i++){
            number[i] = sc.nextInt();
        }

        // 합을 미리 계산해놓기
        // 4~9 까지의 합은 1~9 까지 합에서 1~3까지 합을 빼면 됨

        long[] calsum = new long[100001];
        // calsum[i] 는 1칸부터 i칸 까지의 합이 저장되 있다.
        calsum[1] = number[1];
        for(int i=2;i<=n;i++){
            calsum[i] = calsum[i-1] + number[i];
        }

        long max = 0;
        int maxi = 1;

        for(int i=1;i<=m;i++){
            int left = sc.nextInt();
            int right = sc.nextInt();
            // max값을 sum으로 계속 갱신해서 최종 maxi와 sum을 리턴하기...
            long sum = calsum[right] - calsum[left - 1];
            if(max < sum){
                max = sum;
                maxi = i;
            }
        }

        // 1등의 앨범구매했던 순서 & 점수 출력
        System.out.printf("%d %d", maxi, max);
    }
}