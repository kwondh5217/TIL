import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2225 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n+1][k+1];
        dp[0][0] = 0;
        for(int i = 1; i <= k; i++){
            dp[0][i] = 1;
        }

        for(int i = 1; i <= n; i++){
            for(int j = 0; j <= k; j++){
                for(int a = 0; a <= j; a++){
                    dp[i][j] += dp[i-1][a];
                    dp[i][j] %= 1000000000;
                }
            }
        }

        System.out.println(dp[n][k]%1000000000);
    }
}
