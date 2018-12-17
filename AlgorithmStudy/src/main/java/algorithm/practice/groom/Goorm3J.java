package algorithm.practice.groom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Goorm3J {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int n = sc.nextInt(); //1~1000
        int m = sc.nextInt(); //1~100
        int[] card = new int[n];
        for(int i=0;i<n;i++){
            card[i] = sc.nextInt();
        }

        //a+b 배열 만들기
        List<Integer> sumOfTwo = new ArrayList<>();
        for(int i=0;i<n;i++){ // O(N^2)
            for(int j=0;j<n;j++){
                sumOfTwo.add(card[i] + card[j]);
            }
        }
        // 빨리찾아야 되니깐 (이진탐색) 정렬해놓기
        Collections.sort(sumOfTwo); // O(NlogN)

        List<Integer> answer = new ArrayList<>();
        for(int i=0;i<m;i++){
            int jackpotNum = sc.nextInt();
            // 숫자 네개를 뽑는건 미친짓이다 N의 네제곱걸려...
            // 1000*1000*1000*1000
            // N^3 * logN 도 오래걸림
            // (a+b) + (c+d) 의 경우로 생각해야한다
            // 모든 카드에 대해서 a+b의 경우를 미리 계산해서 저장해두고
            // 그 배열을 돌면서 당첨번호 - (a+b) 가 되는 (c+d)가 있는지 배열에서 탐색
            // a+b 배열을 정렬해놓고 이진 탐색해주면 logN
            // a+b 배열을 만드는 것 자체는 N^2
            // 결국 O(N^2 * N^2logN^2 * M * n^2 * logN^2)
            // 식 정리하고 상수 제거해주면 결국 O(M * N^2) 이다.
            // 100 * 1000 * 1000 = 1억번

            for(int j=0;j<sumOfTwo.size();j++){
                int first = sumOfTwo.get(j);
                int second = jackpotNum - first;
                if(Collections.binarySearch(sumOfTwo, second) >= 0){
                    answer.add(jackpotNum);
                    break;
                }
            }
        }

        Collections.sort(answer);
        if(answer.size() == 0){
            System.out.print("NO");
        }else {
            for(int i=0;i<answer.size();i++){
                if(i>0)
                    System.out.print(" ");
                System.out.print(answer.get(i));
            }
        }
    }
}


