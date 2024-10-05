import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1937 {
    static int n, result;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        result = 0;

        map = new int[n][n];
        dp = new int[n][n];

        for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result = Math.max(dfs(i,j), result);
            }
        }
        System.out.println(result);
    }

    public static int dfs(int x, int y){
        if(dp[x][y] != 0){
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || ny < 0 || nx >= n || ny >= n || map[x][y] >= map[nx][ny]){
                continue;
            }

            dp[x][y] = Math.max(dp[x][y], dfs(nx,ny) + 1);
        }

        return dp[x][y];
    }
}