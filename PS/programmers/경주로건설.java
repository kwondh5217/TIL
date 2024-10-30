import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        int[][][] cost = new int[rows][cols][4];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, -1, 0});
        for (int i = 0; i < 4; i++) {
            cost[0][0][i] = 0;
        }

        int answer = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] poll = q.poll();

            int x = poll[0];
            int y = poll[1];
            int d = poll[2];
            int amount = poll[3];

            if (x == rows - 1 && y == cols - 1) {
                answer = Math.min(answer, amount);
                continue;
            }
            
            if(amount > answer) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!isRange(nx, ny, rows, cols) || board[nx][ny] != 0) continue;

                int additionalCost = amount + 100;
                if (d != -1 && (d != i)) {
                    additionalCost += 500;
                }

                if (additionalCost < cost[nx][ny][i]) {
                    cost[nx][ny][i] = additionalCost;
                    q.offer(new int[]{nx, ny, i, additionalCost});
                }
            }
        }

        return answer;
    }

    private boolean isRange(int x, int y, int rows, int cols) {
        return x >= 0 && y >= 0 && x < rows && y < cols;
    }
}
