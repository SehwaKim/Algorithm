package algorithm.practice.groom;

import java.util.Scanner;


public class Groom2I {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        // 주어진 데이터의 조건을 활용해 반복문을 최소화하자
        // int[] episode를 오름차순으로 정렬한다 10만 제곱의 시간복잡도
        // int[] episode를 돌면서 1씩 커지는지 검사한다 10만의 시간복잡도
        // 결국 최악의 경우 O(n^2) -> 연산횟수가 최대 천억번이 되어서 말이 안된다

        // 주어진 데이터의 조건은 그럼 뭔가?
        // 총 N개의 데이터는 서로 중복되지 않았다는 점!
        // 아 그러면 N개의 데이터에서 가장 작은애랑 가장 큰애를 구해서
        // 걔네의 차가 N이면 참이고 아니면 거짓.... 와우!

        int n = sc.nextInt(); //1~10만
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i=0;i<n;i++){
            int episode = sc.nextInt();
            if(min > episode)
                min = episode;
            if(max < episode)
                max = episode;
        }

        if((max - min + 1) == n)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}
