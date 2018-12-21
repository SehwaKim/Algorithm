package algorithm.practice.groom.recursion;

import java.util.Scanner;

public class Goorm7D {
    // Merge Sort
    // idea : 원소가 하나 혹은 없는 배열은 정렬되어 있다 (천재적이다)
    public static void main(String[] args) {
        // 계속 반으로 쪼개는거지.. base case 에 도달할 때 까지
        // 원소 N개의 배열을 merge sort 하라는 거는
        // 배열을 두동강내서 각각 정렬뒤 합치라는 거
        // 더 작은 범위로 본다면 원소가 1개 or 0개 인 배열 2개를 합치라는 거
        // 배열의 크기가 0~1까지 되도록 계속 이등분 한뒤 (O(logN)) 각각 합쳐주면 (O(N)) 되겠음

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 1~10만 이하 - 배열의 크기
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 함수를 먼저 정의 - 문제가 뭔지 정의
        // 크기가 n인 배열을 머지소트하라 - 정렬의 범위도 정해줘야함 l부터 r까지
        int[] buffer = new int[n];
        mergeSort(arr, 0, arr.length - 1, buffer);

        // 출력
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
    }

    private static void mergeSort(int[] arr, int l, int r, int[] buffer) {
        // base case
        // 배열의 크기가 0 혹은 1이면 그 배열은 정렬된거다
        if (l >= r) {
            return; // 더이상 재귀호출 안시키고 (두동강 안내고) 돌아가면 두개를 이제 합쳐야한다
        }

        // 영역을 반으로 나눌 중간 인덱스를 계산한다
        int m = (l + r) / 2;

        mergeSort(arr, l, m, buffer);
        mergeSort(arr, m + 1, r, buffer);

        // l ~ r 범위를 정렬한다
        concatOrderedArrays(arr, l, m, r, buffer);
    }

    private static void concatOrderedArrays(int[] arr, int l, int m, int r, int[] buffer) {
        //for (int i = l; i < r; i++) {
        //            if (arr[i] > arr[i + 1]) {
        //                int temp = arr[i + 1];
        //                arr[i + 1] = arr[i];
        //                arr[i] = temp;
        //            }
        //        }

        int pl = l; // m 까지 유효
        int pr = m + 1; // r 까지 유효
        int pb = l;

        while (true) {
            if (pl > m) {
                while (pr <= r) {
                    buffer[pb] = arr[pr];
                    pr++;
                    pb++;
                }
                break;
            }

            if (pr > r) {
                while (pl <= m) {
                    buffer[pb] = arr[pl];
                    pl++;
                    pb++;
                }
                break;
            }

            if (arr[pl] < arr[pr]) {
                buffer[pb] = arr[pl];
                pl++;
                pb++;
            } else if (arr[pl] > arr[pr]) {
                buffer[pb] = arr[pr];
                pr++;
                pb++;
            } else if (arr[pl] == arr[pr]) {
                buffer[pb] = arr[pl];
                buffer[pb + 1] = arr[pr];
                pl++;
                pr++;
                pb+=2;
            }
        }

        for (int i = l; i <= r; i++) {
            arr[i] = buffer[i];
        }
    }
}
