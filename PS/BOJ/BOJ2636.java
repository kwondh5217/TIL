import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2636 {
    static int n, m, cnt;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+2][m+2];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= m; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if(num == 1) cnt++;
            }
        }

        int index = 0;
        int hour = 0;

        while(cnt > 0){
            int meltedCnt = bfs();
            hour = cnt;
            cnt -= meltedCnt;
            index++;
        }

        System.out.println(index);
        System.out.println(hour);


    }

    static int bfs(){
        Queue<int[]> q = new ArrayDeque<>();
        Queue<int[]> meltQ = new ArrayDeque<>();
        int melt = 0;
        check = new boolean[n+2][m+2];
        check[0][0] = true;
        q.offer(new int[]{0,0});

        while(!q.isEmpty()){
            int[] poll = q.poll();

            for(int i = 0; i < 4; i++){
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= n+2 || ny >= m+2 || check[nx][ny]) continue;
                if(map[nx][ny] == 1){
                    check[nx][ny] = true;
                    meltQ.offer(new int[]{nx, ny});
                    continue;
                }
                check[nx][ny] = true;
                q.offer(new int[]{nx,ny});
            }
        }

        while(!meltQ.isEmpty()){
            int[] poll = meltQ.poll();
            map[poll[0]][poll[1]] = 0;
            melt++;
        }

        return melt;
    }
}