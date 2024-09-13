import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        int[][] map = new int[n+1][n+1];
        int[][][] dp = new int[3][n+1][n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][1] = 1;
        dp[0][1][2] = 1;

        for(int i = 1; i <= n; i++){
            for(int j = 3; j <= n; j++){
                if(map[i][j] == 1) continue;

                // 가로에서 오는 파이프
                if(map[i][j-1] != 1 && dp[0][i][j-1] > 0){
                    dp[0][i][j] += dp[0][i][j-1];
                }
                if(map[i-1][j] != 1 && map[i][j-1] != 1 && dp[0][i-1][j-1] > 0){
                    dp[1][i][j] += dp[0][i-1][j-1];
                }

                // 대각선, 세로
                dp[0][i][j] += dp[1][i][j-1];
                dp[2][i][j] += dp[1][i-1][j];
                dp[2][i][j] += dp[2][i-1][j];
                if(map[i][j-1] != 1 && map[i-1][j] != 1){
                    dp[1][i][j] += dp[1][i-1][j-1];
                    dp[1][i][j] += dp[2][i-1][j-1];
                }
            }
        }
        
        int ans = 0;
        for(int i = 0; i < 3; i++){
            ans += dp[i][n][n];
        }
        System.out.println(ans);
    }
}
