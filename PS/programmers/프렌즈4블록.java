import java.util.*;

class Solution {
    static boolean[][] toBeRemoved;
    static int[][] map;
    static int cnt;

    public int solution(int m, int n, String[] board) {
        // 초기화
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = (board[i].charAt(j) - 'A') + 1;
            }
        }

        // 검사
        while (true) {
            toBeRemoved = new boolean[m][n];
            boolean hasChange = check(m, n);

            // 변화가 없으면 종료
            if (!hasChange) break;

            remove(m, n);
            move(m, n);
        }

        return cnt;
    }

    private void move(int m, int n) {
        for (int j = 0; j < n; j++) {
            int emptyRow = m - 1;
            for (int i = m - 1; i >= 0; i--) {
                if (map[i][j] != 0) {
                    int temp = map[i][j];
                    map[i][j] = 0;
                    map[emptyRow--][j] = temp;
                }
            }
        }
    }

    private void remove(int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (toBeRemoved[i][j]) {
                    map[i][j] = 0;
                    cnt++;
                }
            }
        }
    }

    private boolean check(int m, int n) {
        boolean hasChange = false;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int a = map[i][j];
                int b = map[i][j + 1];
                int c = map[i + 1][j];
                int d = map[i + 1][j + 1];

                if (a == b && b == c && c == d && a != 0) {
                    toBeRemoved[i][j] = true;
                    toBeRemoved[i][j + 1] = true;
                    toBeRemoved[i + 1][j] = true;
                    toBeRemoved[i + 1][j + 1] = true;
                    hasChange = true;
                }
            }
        }
        return hasChange;
    }
}
