package algorithm.practice.groom;

import java.util.Arrays;
import java.util.Scanner;

public class Goorm3H {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int n = sc.nextInt(); //카드 수 1~10만
        int m = sc.nextInt(); //당첨번호 수 1~100
        int[] card = new int[n];
        for(int i=0;i<n;i++){
            card[i] = sc.nextInt(); //최대 1억
        }
        Arrays.sort(card); //O(NlogN)
        int count = 0;
        for(int i=0;i<m;i++){//1~100
            int jackpotNum = sc.nextInt(); //최대 2억
            for(int j=0;j<n;j++){ // 먼저 당첨번호에서 카드 1개씩 뺀 숫자를 찾아내자

                int secondNum = jackpotNum - card[j];
                // 그리고 이 secondNum이 존재하면 이 당첨번호는 유효한 것이 됨
                // 하지만 카드 10만개를 N^2 도는 반복문은 100억이 넘어가서 안됨
                //for(int w=0;w<n;w++){
                // 이곳이 n번이 걸리게 하기보다는 logN번 걸리게 이진탐색하는게 어떨까. 그럴려면 card배열이 정렬되어있어야한다. -> O(NlogN)
                // O(NlogN) + O(logN) < O(N^2)
                //	if(card[w] == secondNum){
                //		ok = true;
                //		count++;
                //		break;
                //	}
                //}
                if(Arrays.binarySearch(card, secondNum) >= 0){ // O(logN)
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
// 기존 O(M * N^2) = 100 * 10만 * 10만 = 100 * 백억 = 1조
// 개선 O(M * NlogN * N * logN)
// 배열정렬에 O(NlogN)이 걸렸고 원래 N번 돌았어야 할 탐색용 반복문을 이진탐색으로 대체해서 logN번만 돌게 하였음

