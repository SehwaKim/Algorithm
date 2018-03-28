import java.util.Scanner;

public class Sorting_number {
    public static void main(String[] args) {
        //나무 조각 5개 정렬 문제 - bubble sort

        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];
        String input;
        for(int i=0;i<5;i++){
            input = sc.next();
            arr[i] = Integer.parseInt(input);
        }

        int tmp;

        for(int i=0;i<arr.length-1;i++){
            for(int j=1;j<arr.length-i;j++){
                if(arr[j-1]>arr[j]){
                    tmp=arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=tmp;
                    for(int w=0;w<arr.length;w++){
                        System.out.print(arr[w]+" ");
                    }
                    System.out.println("");
                }
            }
        }
    }
}
