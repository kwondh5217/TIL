import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1965 {

    static int n;
    static int[] num;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        num = new int[n];
        dp = new int[n];

        int max = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int tmp = Integer.parseInt(st.nextToken());
            num[i] = tmp;
            dp[i] = 1;

            for(int j = 0; j < i; j++){
                if(num[i] > num[j] && dp[i] < dp[j]+1){
                    dp[i] = dp[j]+1;
                }
            }
        }

        for(int i = 0; i < n; i++){
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
