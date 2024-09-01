import java.util.*;
import java.io.*;

public class BOJ2167 {
    static int n, m;
    static int[][] arr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1][m + 1];
        
        // 누적합 계산
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                arr[i][j] = tmp + arr[i-1][j] + arr[i][j-1] - arr[i-1][j-1];
            }
        }
        
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            // 구간 합 계산
            int result = arr[c][d] - arr[a-1][d] - arr[c][b-1] + arr[a-1][b-1];
            sb.append(result).append("\n");
        }
        
        System.out.print(sb);
    }
}
