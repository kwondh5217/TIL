import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ2302 {

    static int n, m;
    static int[] dp;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        check = new boolean[n+1];

        if(n == 1){
            System.out.println(1);
            System.exit(0);
        }

        for(int i = 0; i < m; i++){
            int tmp = Integer.parseInt(br.readLine());
            check[tmp] = true;
        }

        dp[1] = 1;
        if(check[1] || check[2]){
            dp[2] = 1;
        }else {
            dp[2] = 2;
        }

        for(int i = 3; i <= n; i++){
            if(check[i-1] || check[i]){
               dp[i] = dp[i-1];
            }else {
                dp[i] = dp[i-1] + dp[i-2];
            }
        }

        System.out.println(dp[n]);
    }


}
