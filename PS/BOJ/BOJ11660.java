import java.io.*;
import java.util.*;

public class BOJ11660 {
    static int N, M;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[N+1][N+1];
        int[][] sum = new int[N+1][N+1];
        
        for(int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j = 1; j <= N; j++) {
        		map[i][j] = Integer.parseInt(st.nextToken());
        		sum[i][j] = map[i][j] + (sum[i-1][j] + sum[i][j-1]) - sum[i-1][j-1];
        	}
        }

        for(int i = 0; i < M; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int fx = Integer.parseInt(st.nextToken());
        	int fy = Integer.parseInt(st.nextToken());
        	int tx = Integer.parseInt(st.nextToken());
        	int ty = Integer.parseInt(st.nextToken());
        	
        	int result = sum[tx][ty] - (sum[tx][fy-1] + sum[fx-1][ty]) + sum[fx-1][fy-1];
        	
        	sb.append(result).append("\n");
        
        }
        
        System.out.println(sb);
    }
}
