import java.util.*;
class Solution {
    static final int INF = 9876543;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 0;
        int[][] dist = new int[n+1][n+1];
        for(int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
        }
        for(int i = 0; i < fares.length; i++) {
            int from = fares[i][0];
            int to = fares[i][1];
            int cost = fares[i][2];
            dist[from][to] = cost;
            dist[to][from] = cost;
        }
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                for(int j = 1; j <= n; j++) {
                    if(i == j) dist[i][j] = 0;
                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }
        
        int minIdx = 0;
        int minResult = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            if(i != a && minResult > dist[i][a] + dist[i][b] + dist[s][i]) {
                minIdx = i;
                minResult = dist[i][a] + dist[i][b] + dist[s][i];
            } else if(i != b && minResult > dist[i][a] + dist[i][b] + dist[s][i]) {
                minIdx = i;
                minResult = dist[i][a] + dist[i][b] + dist[s][i];
            }
        }

        return minResult;
    }
}
