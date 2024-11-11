import java.util.*;

class Solution {
    // 상하좌우 4방향 탐색을 위한 dx, dy 설정
    static int[] dx = {1, -1, 0, 0};  
    static int[] dy = {0, 0, 1, -1};
    static final int N = 5;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[N];
        
        // 각 장소(5x5) 탐색
        for (int i = 0; i < N; i++) {
            char[][] map = new char[N][N];
            for (int j = 0; j < N; j++) {
                map[j] = places[i][j].toCharArray();
            }
            
            boolean flag = false;
            // 각 좌표에서 'P'를 찾아서 BFS 탐색 시작
            for (int j = 0; j < N && !flag; j++) {
                for (int k = 0; k < N && !flag; k++) {
                    if (map[j][k] != 'P') continue;  // 'P'인 경우에만 탐색
                    
                    Queue<int[]> q = new LinkedList<>();
                    boolean[][] visited = new boolean[N][N];  // 방문 여부 체크
                    q.offer(new int[]{j, k});
                    visited[j][k] = true;  // 시작 좌표 방문 처리
                    
                    // BFS 탐색 시작
                    while (!q.isEmpty()) {
                        int[] poll = q.poll();
                        int x = poll[0];
                        int y = poll[1];
                        
                        // 현재 좌표와의 거리가 2 이내인 경우만 탐색
                        for (int d = 0; d < 4; d++) {  // 상하좌우 4방향 탐색
                            int nx = x + dx[d];
                            int ny = y + dy[d];
                            
                            // 범위 체크
                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                            if (visited[nx][ny]) continue;  // 이미 방문한 곳 무시
                            
                            // 거리 계산 (맨해튼 거리)
                            int dist = Math.abs(j - nx) + Math.abs(k - ny);
                            if (dist > 2) continue;  // 거리가 2보다 큰 경우 무시
                            
                            // 파티션 'X'가 있으면 통과 불가
                            if (map[nx][ny] == 'X') continue;
                            
                            // 거리 2 이내에서 'P' 발견 시 거리두기 위반
                            if (map[nx][ny] == 'P') {
                                flag = true;
                                break;
                            }
                            
                            // 계속 탐색
                            visited[nx][ny] = true;
                            q.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
            
            // 거리두기 위반 여부에 따른 결과 저장
            answer[i] = flag ? 0 : 1;
        }
        
        return answer;
    }
}
