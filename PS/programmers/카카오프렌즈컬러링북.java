import java.util.*;
class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public int[] solution(int m, int n, int[][] picture) {
        boolean[][] check = new boolean[m][n];
        int max = 0;
        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(check[i][j] || picture[i][j] == 0) continue;
                count++;
                
                Queue<int[]> q = new LinkedList<>();
                q.offer(new int[]{i, j});
                check[i][j] = true;
                int cnt = 1;
                while(!q.isEmpty()) {
                    int[] poll = q.poll();
                    
                    for(int k = 0; k < 4; k++) {
                        int nx = poll[0] + dx[k];
                        int ny = poll[1] + dy[k];
                        
                        if(nx < 0 || ny < 0 || nx >= m || ny >= n) continue;
                        if(check[nx][ny]) continue;
                        if(picture[i][j] != picture[nx][ny] || picture[nx][ny] == 0) continue;
                        check[nx][ny] = true;
                        cnt++;
                        q.offer(new int[]{nx, ny});
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        int[] answer = {count, max};
        return answer;
    }
}