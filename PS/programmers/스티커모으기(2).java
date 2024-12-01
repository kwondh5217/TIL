class Solution {
    public int solution(int sticker[]) {
        if(sticker.length == 1) {
            return sticker[0];
        }
        int[][] dp = new int[2][sticker.length];
        dp[0][0] = sticker[0];
        dp[0][1] = sticker[0];
        
        dp[1][0] = 0;
        dp[1][1] = sticker[1];
        
        for(int i = 2; i < dp[0].length; i++) {
            if(i < sticker.length - 1) {
                dp[0][i] = Math.max(dp[0][i - 1], dp[0][i - 2] + sticker[i]);
            } else {
                dp[0][i] = Math.max(dp[0][i - 1], dp[0][i - 2]);
            }
            dp[1][i] = Math.max(dp[1][i - 1], dp[1][i - 2] + sticker[i]);
        }
        
        int answer = Math.max(dp[0][sticker.length - 1], dp[1][sticker.length - 1]);

        return answer;
    }
}