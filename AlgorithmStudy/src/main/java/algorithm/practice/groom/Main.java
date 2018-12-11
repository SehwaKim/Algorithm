package algorithm.practice.groom;

import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    public static final int MAX_TABLE_LENGTH = 10000;

    public static void main(String[] args){
        int[] count = new int[MAX_TABLE_LENGTH];
        int n = sc.nextInt(); // 1~10만

        for(int i=0;i<n;i++){
            int number = sc.nextInt();
            count[number] = count[number] + 1;
        }

        int frequent = getMostFrequentNumber(count);
        System.out.printf("%04d", frequent);
    }

    private static int getMostFrequentNumber(int[] count){
        int max = count[0];
        int answer = 0;

        for(int i=1;i<MAX_TABLE_LENGTH;i++){
            if(max < count[i]){
                max = count[i];
                answer = i;
            }
        }

        return answer;
    }
}

