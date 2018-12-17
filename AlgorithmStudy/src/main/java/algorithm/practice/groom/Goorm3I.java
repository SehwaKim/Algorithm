package algorithm.practice.groom;

import java.util.*;

public class Goorm3I {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int n = sc.nextInt(); //1~1000
        int m = sc.nextInt(); //1~100
        int[] card = new int[n];

        for(int i=0;i<n;i++){
            card[i] = sc.nextInt();
        }
        Arrays.sort(card); // O(NlogN)

        List<Integer> answer = new ArrayList<>();
        for(int i=0;i<m;i++){
            int jackpotNum = sc.nextInt();
            boolean ok = false;
            // 카드 세개를 뽑아서 jackpotNum이 되는지 확인하기... N의 세제곱이 걸리게 할꺼야?
            // a+b+c 중에서 a+b의 경우만 구하고(N^2)
            // 남은 수 c를 N번 걸려서 찾지말고 미리 정렬해둔 배열에서 logN만에 찾기...
            for(int j=0;j<n;j++){
                int x = card[j];
                for(int w=0;w<=j;w++){ // 여기서 두번째 카드는 왜 첫번째 카드보다 같거나 적은 숫자 범위에서만 고르나?
                    int y = card[w];
                    int z = jackpotNum - (x + y);
                    if(Arrays.binarySearch(card, z) >= 0){
                        ok = true;
                        break;
                    }
                }
                if(ok){ break; }
            }
            if(ok){
                answer.add(jackpotNum);
            }
        }

        Collections.sort(answer);

        if(answer.size() == 0){
            System.out.print("NO");
        }else {
            for(int i=0;i<answer.size();i++){
                if(i > 0)
                    System.out.print(" ");
                System.out.print(answer.get(i));
            }
        }
    }
}
// 카드 세개를 뽑아서 그 더한 값이 당첨번호가 되는지 계산하려면, N의 세제곱이 걸린다
// N^3 보다는 N^2 * logN 이 낫다.
// 두 숫자만 N^2로 구하고 당첨번호가 되려면 필요한 나머지 숫자 하나를 정렬된 배열에서 이진탐색한다
// 기존 O(M * N^3)은 100 * 10억 = 천억번
// 개선 O(M * N^2 * logN) 은 100 * 백만번 * 상수 = 1억번