import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ17484 {

    static int n, m;
    static int[][] map;
    static int[] dx = {0, 1, 1, 1};
    static int[] dy = {0, -1, 0, 1};
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < m; i++){
            Queue<int[]> q = new ArrayDeque<>();
            q.offer(new int[]{0, i, 0, map[0][i]}); // x, y, direction, sum
            int tmp = Integer.MAX_VALUE;
            while(!q.isEmpty()){
                int[] poll = q.poll();
                int x = poll[0];
                int y = poll[1];
                int d = poll[2];
                int sum = poll[3];

                if(x == n-1) {
                    tmp = Math.min(tmp, sum);
                    continue;
                }

                for(int j = 1; j < 4; j++){
                    if(d == j) continue;
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if(nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                    q.offer(new int[]{nx, ny, j, sum+map[nx][ny]});
                }
            }
            result = Math.min(result, tmp);
        }

        System.out.println(result);
    }
}