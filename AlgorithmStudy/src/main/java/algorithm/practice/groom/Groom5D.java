package algorithm.practice.groom;

import java.util.Scanner;

public class Groom5D {
    public static final Scanner scanner = new Scanner(System.in);

    public static void testCase() {
        int N = scanner.nextInt();

        GameMap gameMap = new GameMap(N, N);

        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                int buildings = scanner.nextInt();
                gameMap.setBuildingsAt(r, c, buildings);
            }
        }

        int answer = 0;

        for (int r = 0; r < N; r += 1) {
            for (int c = 0; c < N; c += 1) {
                // 해당 위치에서 방어할 수 있는 모든 건물의 수
                int numberOfBuildings = gameMap.getTotalPointAt(r, c);

                // 최대값을 갱신한다
                answer = Math.max(answer, numberOfBuildings);
            }
        }

        System.out.println(answer);
    }

    public static void main(String[] args) throws Exception {
        int caseSize = scanner.nextInt();

        for (int caseIndex = 1; caseIndex <= caseSize; caseIndex += 1) {
            testCase();
        }
    }

}

class GameMap {
    public final int rows;
    public final int columns;
    private final int[][] buildings;   // buildings[r][c] := (r, c)에 존재하는 건물 수

    private final int[] rowSums;    //rowSums[r] := r행에 존재하는 건물의 수
    private final int[] colSums;    //colSums[c] := c열에 존재하는 건물의 수
    private final int[] diagonalSumA; //diagonalSumA[k] := k번째 ↙ 방향 대각선상에 존재하는 건물의 수
    private final int[] diagonalSumB; //diagonalSumB[k] := k번째 ↘ 방향 대각선상에 존재하는 건물의 수


    public GameMap(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.buildings = new int[rows][columns];
        this.rowSums = new int[rows];
        this.colSums = new int[columns];
        this.diagonalSumA = new int[rows + columns];
        this.diagonalSumB = new int[rows + columns];
    }

    public int getBuildingsAt(int r, int c) {
        if (this.isInside(r, c) == false)
            return 0;

        return this.buildings[r][c];
    }

    public void setBuildingsAt(int r, int c, int value) {
        if (this.isInside(r, c) == false) {
            return;
        }

        int origin = getBuildingsAt(r, c);  // 수정 전 건물 수
        int delta = value - origin;         // 수정 전/후 건물수의 차

        this.buildings[r][c] = value; // 해당 칸의 건물 수 정보 수정
        this.rowSums[r] += delta;       // 해당 행의 총 건물 수 수정
        this.colSums[c] += delta;       // 해당 열의 총 건물 수 수정
        this.diagonalSumA[r + c] += delta;  // 해당 대각선의 총 건물 수 수정
        this.diagonalSumB[c - r + rows - 1] += delta; // 해당 대각선의 총 건물 수 수정
    }

    public int getTotalPointAt(int r, int c) {
        int buildings = 0;
        buildings += this.rowSums[r];   // 가로 방향으로 방어 가능한 건물 수
        buildings += this.colSums[c];   // 세로 방향으로 방어 가능한 건물 수
        buildings += this.diagonalSumA[r + c];  // ↙ 방향으로 방어 가능한 건물 수
        buildings += this.diagonalSumB[c - r + rows - 1]; // ↘ 방향으로 방어 가능한 건물 수

        // 중점의 경우 네 번 중복으로 계산되었기 때문에 감산해준다.
        buildings -= getBuildingsAt(r, c) * 3;

        return buildings;
    }

    public boolean isInside(int r, int c) {
        if (r < 0 || r >= this.rows)
            return false;
        if (c < 0 || c >= this.columns)
            return false;
        return true;
    }
}