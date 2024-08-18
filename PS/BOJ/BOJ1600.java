import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1600 {

    static int k, w, h;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[] horseDx = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] horseDy = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[][] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];

        for(int i = 0; i < h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < w; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Monkey> q = new ArrayDeque<>();
        Monkey monkey = new Monkey(0, 0, k, 0);
        boolean[][][] check = new boolean[k+1][h][w];
        check[k][0][0] = true;

        q.offer(monkey);

        while(!q.isEmpty()){
            Monkey poll = q.poll();

            if(poll.x == h-1 && poll.y == w-1){
                min = Math.min(min, poll.result);
                break;
            }

            for(int i = 0; i < 4; i++){
                int x = poll.x + dx[i];
                int y = poll.y + dy[i];

                if(!isRange(x,y) || map[x][y] == 1) continue;
                if(check[poll.cnt][x][y]) continue;

                check[poll.cnt][x][y] = true;
                q.offer(new Monkey(x, y, poll.cnt, poll.result+1));
            }

            for(int i = 0; i < 8; i++){
                int x = poll.x + horseDx[i];
                int y = poll.y + horseDy[i];

                if(!isRange(x,y) || map[x][y] == 1) continue;
                if(poll.cnt == 0 || check[poll.cnt-1][x][y]) continue;


                check[poll.cnt-1][x][y] = true;
                q.offer(new Monkey(x, y, poll.cnt-1, poll.result+1));
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= h || y >= w) return false;
        return true;
    }

    static class Monkey {
        int x;
        int y;
        int cnt;
        int result;

        public Monkey(int x, int y, int cnt, int result){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.result = result;
        }
    }
}
