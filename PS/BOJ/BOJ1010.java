import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ1010 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int max = 30;
        int[][] dp = new int[max][max];

        for(int i = 0; i < max; i++){
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for(int i = 2; i < max; i++){
            for(int j = 1; j < max; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }


        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            System.out.println(dp[M][N]);
            sb.append(dp[M][N]);
        }
    }
}
