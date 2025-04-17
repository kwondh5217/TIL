import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            if(i - a - 1 >= 0) {
                dp[i] = Math.min(dp[i - a - 1] + 1, dp[i]);
            }
            if(i - b - 1 >= 0) {
                dp[i] = Math.min(dp[i - b - 1] + 1, dp[i]);
            }
        }
        System.out.println(dp[n]);
    }
}
