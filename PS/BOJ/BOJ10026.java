import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class BOJ10026 {

    static int n;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][][] map;
    static boolean[][][] check;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        map = new char[2][n][n];
        check = new boolean[2][n][n];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < n; j++){
                map[0][i][j] = str.charAt(j);
                map[1][i][j] = str.charAt(j);
                if(str.charAt(j) == 'G'){
                    map[1][i][j] = 'R';
                }
            }
        }

        int red = 0;
        int green = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(!check[0][i][j]){
                    redBfs(i,j);
                    red++;
                }
                if(!check[1][i][j]){
                    greenBfs(i,j);
                    green++;
                }
            }
        }

        sb.append(red).append(" ").append(green);
        System.out.println(sb);




    }

    static void redBfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();

        check[0][x][y] = true;
        q.offer(new int[]{x, y});

        while(!q.isEmpty()){
            int[] poll = q.poll();
            char ch = map[0][poll[0]][poll[1]];

            for(int i = 0; i < 4; i++){
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                    continue;
                }
                if(map[0][nx][ny] != ch || check[0][nx][ny]) continue;
                check[0][nx][ny] = true;
                q.offer(new int[]{nx,ny});
            }
        }
    }
    static void greenBfs(int x, int y){
        Queue<int[]> q = new ArrayDeque<>();

        check[1][x][y] = true;
        q.offer(new int[]{x, y});

        while(!q.isEmpty()){
            int[] poll = q.poll();
            char ch = map[1][poll[0]][poll[1]];

            for(int i = 0; i < 4; i++){
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];

                if(nx < 0 || ny < 0 || nx >= n || ny >= n){
                    continue;
                }
                if(map[1][nx][ny] != ch || check[1][nx][ny]) continue;
                check[1][nx][ny] = true;
                q.offer(new int[]{nx,ny});
            }
        }
    }


}
