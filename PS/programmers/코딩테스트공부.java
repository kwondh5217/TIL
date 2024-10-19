import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0;
        int maxCop = 0;
        for (int i = 0; i < problems.length; i++) {
            int alpReq = problems[i][0];
            int copReq = problems[i][1];
            maxAlp = Math.max(maxAlp, alpReq);
            maxCop = Math.max(maxCop, copReq);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);
        
        if (alp >= maxAlp && cop >= maxCop) {
            return 0;
        }

        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, 100000);
        }
        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                for (int[] problem : problems) {
                    int a = problem[0];
                    int b = problem[1]; 
                    int c = problem[2];
                    int d = problem[3]; 
                    int e = problem[4];

                    if (i >= a && j >= b) {
                        int newAlp = Math.min(maxAlp, i + c);
                        int newCop = Math.min(maxCop, j + d);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + e);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
