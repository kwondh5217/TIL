import java.util.*;

class Solution {
    
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String[] dist = {"d", "l", "r", "u"};
    static String answer;
    static int R, C, N, M;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        R = r;
        C = c;
        N = n;
        M = m;
        int dis = Math.abs(x - r) + Math.abs(y - c);
        if(k - dis < 0 || (k - dis) % 2 == 1) {
            return "impossible";
        }
        
        dfs(new Node(x, y, "", k));
        
        return answer;
    }
    
    private void dfs(Node node) {
        if(answer != null) return;
        if(node.cnt == 0) {
            if(node.x == R && node.y == C) {
                answer = node.str;
            }
            return;
        }
        
        // 가지 치기
        int dis = Math.abs(node.x - R) + Math.abs(node.y - C);
        if(node.cnt < dis) return;
        
        for(int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];
            if(nx <= 0 || ny <= 0 || nx > N || ny > M) continue;
            String str = node.str + dist[i];
            dfs(new Node(nx, ny, str, node.cnt - 1));
        }
    }
    
    class Node {
        int x;
        int y;
        String str;
        int cnt;
        
        public Node (int x, int y, String str, int cnt) {
            this.x = x;
            this.y = y;
            this.str = str;
            this.cnt = cnt;
        }
    }
}
