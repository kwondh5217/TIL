import java.util.*;

class Solution {
    static int N, M;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        map = new char[N][M];
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = storage[i].charAt(j);
            }
        }
        
        for(int i = 0; i < requests.length; i++) {
            char target = requests[i].charAt(0);
            List<int[]> list = getContainers(target, requests[i].length() == 2);
            update(list);
        }
        
        int answer = 0;
        
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] != ' ') answer++; 
            }
        }
        return answer;
    }
    
    private static List<int[]> getContainers(char ch, boolean isCrain) {
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                
                if(map[i][j] == ch) {
                    if(!isCrain) {
                        if(check(i, j)) {
                            list.add(new int[]{i, j});
                        }
                    } else {
                        list.add(new int[]{i, j});
                    }
                }
            }
        }
        return list;
    }
    
    private static boolean check(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visit = new boolean[N][M];
        q.offer(new int[]{x, y});
        visit[x][y] = true;
        
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            for(int k = 0; k < 4; k++) {
                int nx = poll[0] + dx[k];
                int ny = poll[1] + dy[k];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    return true;
                }
                if(map[nx][ny] != ' ' || visit[nx][ny]) continue;
                visit[nx][ny] = true;
                q.offer(new int[]{nx, ny});
            }
        }
        return false;
    }
    
    private static void update(List<int[]> list) {
        for(int[] arr : list) {
            map[arr[0]][arr[1]] = ' ';
        }
    }
}
