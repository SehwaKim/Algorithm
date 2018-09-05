package kakao.stage1;

/*
4
5
[“CCBDE”, “AAADE”, “AAABF”, “CCBBF”]
14

6
6
[“TTTANT”, “RRFACC”, “RRRFCC”, “TRRRAA”, “TTMMMF”, “TMMTTJ”]
15
* */
public class Ex06 {
    // 프렌즈4블록 난이도 상
    public static void main(String[] args) {
        int m = 4;
        int n = 3;
        String[] board = {"AAA", "FFF", "FFF", "AAA"};

        /*int m = 6;
        int n = 6;
        String[] board = {"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"};*/

        int result = new Ex06().solution(m, n, board);
        System.out.println(result);
    }

    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];
        int[][] check = new int[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int hitTime = -1;
        int current = 0;
        int[] dy = {0, 0, 1, 1};
        int[] dx = {0, 1, 0, 1};

        while (hitTime != 0) {
            hitTime = 0;
            current++;
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    boolean hit = true;
                    char c = map[i][j];
                    for (int w = 0; w < 4; w++) {
                        char compare = map[i + dy[w]][j + dx[w]];
                        if (compare != c) { // 다른 캐릭이면 실패
                            hit = false;
                            break;
                        }
                        int checkNum = check[i + dy[w]][j + dx[w]];
                        if (checkNum != 0 && checkNum != current) { // 같은 캐릭이어도 빈칸이면 실패
                            hit = false;
                            break;
                        }
                    }
                    // 4가지 좌표 비교 끝
                    if (hit) {
                        hitTime++;
                        for (int w = 0; w < 4; w++) {
                            check[i + dy[w]][j + dx[w]] = current; // current 번째 때 지워진 블록임을 표시
                        }
                    }
                }
            }

            // 한 바퀴 다 돌면 자리이동
            for (int i = 0; i < n; i++) { // x축
                int switchCnt = Integer.MIN_VALUE;

                while (true) {
                    if (switchCnt == 0) {
                        break;
                    }

                    switchCnt = 0;

                    for (int j = 0; j < m - 1; j++) { // y축
                        if (check[j][i] == 0 && check[j + 1][i] != 0) { // 위가 0이고 아래가 1이상인 경우
                            // 자리바꾸기
                            switchCnt++;
                            check[j][i] = check[j + 1][i];
                            check[j + 1][i] = 0;
                            char temp = map[j][i];
                            map[j][i] = map[j + 1][i];
                            map[j + 1][i] = temp;
                        }
                    }
                }
            }
        }

        // 빈칸 세기
        int answer = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (check[i][j] != 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}