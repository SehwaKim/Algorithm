package algorithm.practice.groom;

import java.util.Scanner;

public class Groom3E {
    public static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int t = sc.nextInt();

        for(int i=0;i<t;i++){
            int n = sc.nextInt(); // 1~100

            int[][] paper = new int[101][101];

            for(int j=0;j<n;j++){
                int lMargin = sc.nextInt(); //1~90
                int bMargin = sc.nextInt(); //1~90
                for(int w=lMargin; w<10+lMargin; w++){
                    for(int q=bMargin; q<10+bMargin; q++){
                        paper[w][q] += 1;
                    }
                }
            }

            // 100*100 = 10000번?
            int sum = 0;
            for(int w=1;w<100;w++){
                for(int j=1;j<100;j++){
                    if(paper[w][j] != 0)
                        sum++;
                }
            }

            System.out.println(sum);
        }
    }
}
