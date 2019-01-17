package algorithm.practice.groom.recursion;

import java.util.Scanner;

public class Goorm7F {
    // 히스토그램 문제

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            testCase();
        }
    }

    private static void testCase() {
        int n = sc.nextInt(); //1~10만 기둥수
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt(); // 1~10만 기둥 높이
        }

        // 문제를 정의하라.... 그리고 더 작은 범위에 대해서 똑같이 풀 수 있을지 생각해봐라....
        // 주어진 히스토그램의 l ~ r 범위에서 존재할 수 있는 가장 넓은 직사각형의 넓이를 반환하는 함수
        long maxArea = getLargestRectangleArea(heights, 0, heights.length - 1);
        System.out.println(maxArea);
    }

    private static long getLargestRectangleArea(int[] heights, int l, int r) {
        // base case
        // 히스토그램이 없다면 넓이는 0
        if (l > r) {
            return 0;
        }
        // 직사각형이 1개 일때는 그 1개의 높이 자체가 가장 큰 직사각형의 넓이가 된다
        if (l == r) {
            return heights[l];
        }

        int m = (l + r) / 2;
        long leftSideArea = getLargestRectangleArea(heights, l, m);
        long rightSideArea = getLargestRectangleArea(heights, m + 1, r);

        // 초기 영역의 높이와 넓이를 저장해 계산한다
        int height = Math.min(heights[m], heights[m + 1]);
        long globalMax = height * 2;

        // 좌, 우로 넓이를 늘려가며 확장해나간다
        int lp = m;
        int rp = m + 1;
        int maxWidth = r - l + 1;
        for (int width = 3; width <= maxWidth; width += 2) {
            // 좌, 우 각각 해당 방향으로 범위를 확대했을 때 만날 수 있는 막대의 높이를 구한다
            int lh = lp > l ? heights[lp - 1] : -1;
            int rh = rp < r ? heights[rp + 1] : -1;

            if (lh >= rh) { // 왼쪽으로 확장이 더 유리한 경우
                lp--;
                height = Math.min(heights[lp], height);
            } else { // 오른쪽으로 확장이 더 유리한 경우
                rp++;
                height = Math.min(heights[rp], height);
            }

            // 이 때의 넓이를 계산하고 갱신한다
            long area = (long) height * width;
            globalMax = Math.max(globalMax, area);
        }

        long largest = leftSideArea;
        if(largest < rightSideArea) largest = rightSideArea;
        if(largest < globalMax) largest = globalMax;

        return largest;
    }
}