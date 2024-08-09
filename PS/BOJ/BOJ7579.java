import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ7579 {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] memories = new int[n];
        int[] times = new int[n];
        int[][] dp = new int[n][100001];

        int result = Integer.MAX_VALUE;


        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < n; j++){
            memories[j] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int j = 0; j < n; j++){
            times[j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0;i < n; i++){
            int cost = times[i];
            int memory = memories[i];

            for(int j = 0; j <= 10000; j++){
                if(i == 0){
                    if(j >= cost) dp[i][j] = memory;
                }else {
                    if( j >= cost) dp[i][j] = Math.max(dp[i-1][j-cost] + memory,
                            dp[i-1][j]);
                    else dp[i][j] = dp[i-1][j];
                }
                if(dp[i][j] >= m) result = Math.min(result, j);
            }
        }
        System.out.println(result);

    }
}
