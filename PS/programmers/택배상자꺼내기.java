class Solution {
    public int solution(int n, int w, int num) {
        int m = (n % w) == 0 ? n / w : (n / w) + 1;
        int[][] map = new int[m][w];
        
        int start = 1;
        boolean isRight = true;
        int[] memo = new int[2];
        for(int i = 0; i < m; i++) {
            if(isRight) {
                for(int j = 0; j < w && start <= n; j++) {
                    map[i][j] = start++;
                    if(map[i][j] == num) {
                        memo[0] = i;
                        memo[1] = j;
                    }
                }
            } else {
                for(int j = w - 1; j >= 0 && start <= n; j--) {
                    map[i][j] = start++;
                    if(map[i][j] == num) {
                        memo[0] = i;
                        memo[1] = j;
                    }
                }
            }
            isRight = isRight ? false : true;
        }
        
        int answer = 0;
        for(int i = memo[0]; i < m; i++) {
            if(map[i][memo[1]] != 0) answer++;
        }
        
        
        return answer;
    }
}
