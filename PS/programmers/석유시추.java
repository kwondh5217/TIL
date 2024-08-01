import java.util.*;

class Solution {
    
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static boolean[][] visit;
    public int solution(int[][] land) {
        int answer = 0;
        
        for(int i = 0; i < land[0].length; i++) {
            visit = new boolean[land.length][land[0].length];
            int result = 0;
            for(int j = 0; j < land.length; j++) {
                if(visit[j][i] || land[j][i] == 0) {
                    continue;
                }
                result += bfs(j, i, land);
            }
            answer = Math.max(answer, result);
        }
        
        return answer;
    }
    
    private int bfs(int x, int y, int[][] land) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        visit[x][y] = true;
        int result = 0;
        
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int cx = current[0];
            int cy = current[1];
            result++;
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx < 0 || ny < 0 || nx >= land.length || ny >= land[0].length) {
                    continue;
                }
                if (visit[nx][ny] || land[nx][ny] == 0) {
                    continue;
                }
                
                visit[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        
        return result;
    }
}

