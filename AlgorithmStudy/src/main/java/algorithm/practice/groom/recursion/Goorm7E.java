package algorithm.practice.groom.recursion;

import java.util.Scanner;

public class Goorm7E {
    // Quick Sort 구현하기
    // idea : 원소가 0 혹은 1개인 배열은 정렬되어 있다 (Merge Sort 와 같음)

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 1~10만
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 문제의 정의 (함수의 정의)
        // 배열 arr 의 l ~ r 범위를 quick sort 하시오
        quickSort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            if (i > 0) {
                System.out.print(" ");
            }
            System.out.print(arr[i]);
        }
    }

    private static void quickSort(int[] arr, int l, int r) {
        // base case
        // pivot 과의 접합은 두 배열이 정렬 완료됐을 때 일어난다
        // 원소가 1개인 배열은 정렬된 배열이다
        if (l >= r) {
            return; // 더 이상 pivot 을 뽑아서 나누지 안아도 된다
        }

        // 먼저 할 일은 pivot 을 기준으로 나눠야 한다
        int pivotIndex = getPivotIndex(arr, l, r);

        // pivot 을 기준으로 왼쪽 그룹과 오른쪽 그룹이 계속 swap 되게 하는 함수
        pivotIndex = divideArrayByPivot(arr, l, r, pivotIndex);
        // 여기서 pivot 의 위치는 갱신되게 된다 swap 으로 인해 배열이 재정렬 될 테니깐

        // 나누고 나서 quickSort 각각 호출
        quickSort(arr, l, pivotIndex);
        quickSort(arr, pivotIndex + 1, r);

        // 그리고 나서 나뉜 두 배열이 정렬됐으므로(재귀적으로 정렬 다하고 옴) pivot 이랑 합쳐주면 됨
    }

    private static int divideArrayByPivot(int[] arr, int l, int r, int pivotIndex) {
        int pl = l; //계속 피벗쪽으로 ++ 될거임
        int pr = r; //계속 피벗쪽으로 -- 될거임

        int pivot = arr[pivotIndex];

        while (pl <= pr) {
            while (arr[pl] < pivot) pl++;
            while (arr[pr] > pivot) pr--;
            if (pl <= pr) {
                swap(arr, pl++, pr--);
            }
        }

        return (pl + pr) / 2;
    }

    private static void swap(int[] arr, int pl, int pr) {
        int temp = arr[pr];
        arr[pr] = arr[pl];
        arr[pl] = temp;
    }

    private static int getPivotIndex(int[] arr, int l, int r) {
        // pivot 을 정할 때 단순히 (left + right)/2 를 해서 정해버리면 한쪽으로 치우쳐진 분할을 반복하게 될 수도 있다
        // 그런 경우를 개선하기 위해서 이런 방법을 쓴다
        // 나눌 배열의 요소갯수가 3 이상이면 임의로 요소 3을 선택하고 그 중에서 중앙값인 요소를 피벗으로 선택한다

        int m = (l + r) / 2;

        if (arr[l] <= arr[m] && arr[m] <= arr[r]) {
            return m;
        }

        if (arr[l] <= arr[r] && arr[r] <= arr[m]) {
            return r;
        }

        return l;
    }
}
