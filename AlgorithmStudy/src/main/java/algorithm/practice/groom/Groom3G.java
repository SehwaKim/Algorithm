package algorithm.practice.groom;

import java.util.*;

public class Groom3G {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int[] birth = new int[200000];

        int n = sc.nextInt(); //1~20만
        int k = sc.nextInt(); //1~20만

        for(int i=0;i<n;i++){
            birth[i] = sc.nextInt();
        }

        int[] birthCnt = new int[1000000];
        int cnt = 0; //경우의 수
        int uniqueCnt = 0;

        for(int i=0;i<k;i++){
            birthCnt[birth[i]] += 1;
            if (birthCnt[birth[i]] == 1) {
                uniqueCnt++;
            } else {
                uniqueCnt--;
            }
        }

        if(uniqueCnt == k) cnt++;

        for(int i=k;i<n;i++){
            //맨 왼쪽에 있던 주민번호 제외하기
            birthCnt[birth[i-k]] -= 1;
            if(birthCnt[birth[i-k]] == 0){
                uniqueCnt--;
            }
            if(birthCnt[birth[i-k]] == 1){
                uniqueCnt++;
            }

            birthCnt[birth[i]] += 1;

            if(birthCnt[birth[i]] == 1){
                uniqueCnt++;
            }else {
                uniqueCnt--;
            }

            if(uniqueCnt == k) { //여전히 k개의 유니크한 주민번호 수가 유지된다면
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}