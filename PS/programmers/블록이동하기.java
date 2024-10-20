import java.util.*;
class Solution {
    static int[][] map;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public int solution(int[][] board) {
        map = board;
        int tx = board.length - 1;
        int ty = board[0].length - 1;
        Map<String, Boolean> visit = new HashMap<>();
        Queue<int[]> q = new LinkedList<>();
        int[] start = new int[]{0, 0, 0, 1, 0, 0};
        q.offer(start);
        visit.put(getKey(start), true);
        int answer = Integer.MAX_VALUE;
        
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            
            int x1 = poll[0];
            int y1 = poll[1];
            int x2 = poll[2];
            int y2 = poll[3];
            int type = poll[4];
            int cnt = poll[5];
            
            if((x1 == tx && y1 == ty) || (x2 == tx && y2 == ty)){
                return cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int nx1 = x1 + dx[i];
                int ny1 = y1 + dy[i];
                int nx2 = x2 + dx[i];
                int ny2 = y2 + dy[i];
                
                if(!(isRange(nx1, ny1) && isRange(nx2, ny2))) continue;
                if(map[nx1][ny1] == 1 || map[nx2][ny2] == 1) continue;
                int[] next = new int[]{nx1, ny1, nx2, ny2};
                if(visit.getOrDefault(getKey(next), false)) continue;
                visit.put(getKey(next), true);
                q.offer(new int[]{nx1, ny1, nx2, ny2, type, cnt + 1});
            }
            
            // 가로로 놓여있을 때
            if(type == 0) {
                if(isRange(x1 - 1, y1) && map[x1 - 1][y1] == 0 && map[x2 - 1][y2] == 0) {
                    int[] next = new int[]{x1 - 1, y1, x1, y1};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x1 - 1, y1, x1, y1, 1, cnt + 1});
                }
                if(isRange(x1 + 1, y1) && map[x1 + 1][y1] == 0 && map[x2 + 1][y2] == 0) {
                    int[] next = new int[]{x1, y1, x1 + 1, y1};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x1, y1, x1 + 1, y1, 1, cnt + 1});
                }
                if(isRange(x2 - 1, y2) && map[x2 - 1][y2] == 0 && map[x1 - 1][y1] == 0) {
                    int[] next = new int[]{x2 - 1, y2, x2, y2};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x2 - 1, y2, x2, y2, 1, cnt + 1});
                }
                if(isRange(x2 + 1, y2) && map[x2 + 1][y2] == 0 && map[x1 + 1][y1] == 0) {
                    int[] next = new int[]{x2, y2, x2 + 1, y2};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x2, y2, x2 + 1, y2, 1, cnt + 1});
                }
                // 세로로 놓여있을 때
            } else {
                if(isRange(x1, y1 - 1) && map[x1][y1 - 1] == 0 && map[x2][y2 - 1] == 0) {
                    int[] next = new int[]{x1, y1 - 1, x1, y1};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x1, y1 - 1, x1, y1, 0, cnt + 1});
                }
                if(isRange(x1, y1 + 1) && map[x1][y1 + 1] == 0 && map[x2][y2 + 1] == 0) {
                    int[] next = new int[]{x1, y1, x1, y1 + 1};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x1, y1, x1, y1 + 1, 0, cnt + 1});
                }
                if(isRange(x2, y2 - 1) && map[x2][y2 - 1] == 0 && map[x1][y1 - 1] == 0) {
                    int[] next = new int[]{x2, y2 - 1, x2, y2};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x2, y2 - 1, x2, y2, 0, cnt + 1});
                }
                if(isRange(x2, y2 + 1) && map[x2][y2 + 1] == 0 && map[x1][y1 + 1] == 0) {
                    int[] next = new int[]{x2, y2, x2, y2 + 1};
                    if(visit.getOrDefault(getKey(next), false)) continue;
                    visit.put(getKey(next), true);
                    q.offer(new int[]{x2, y2, x2, y2 + 1, 0, cnt + 1});
                }
            }

        }

        return answer;
    }

    private boolean isRange(int x, int y) {
        if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) {
            return false;
        }
        return true;
    }
    
    private String getKey(int[] arr) {
        return arr[0] + "," + arr[1] + "," + arr[2] + "," + arr[3];
    }
}
