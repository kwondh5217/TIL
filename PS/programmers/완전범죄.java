import java.util.*;

class Solution {
    static int[][] INFO;
    static int N, M, len;
    static final int INF = 1000000000;
    static int[][][] dp;
    
    public int solution(int[][] info, int n, int m) {
        INFO = info;
        N = n;
        M = m;
        len = info.length;
        
        dp = new int[len + 1][N + 1][M + 1];
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j <= N; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        int res = f(0, 0, 0);
        return res >= INF ? -1 : res;
    }
    
    private int f(int idx, int a, int b) {
        if(a >= N || b >= M) return INF;
        
        if(idx == len) return a;
        
        if(dp[idx][a][b] != -1) return dp[idx][a][b];
        
        int optionA = f(idx + 1, a + INFO[idx][0], b);
        int optionB = f(idx + 1, a, b + INFO[idx][1]);
        
        int res = Math.min(optionA, optionB);
        dp[idx][a][b] = res;
        return res;
    }
}
