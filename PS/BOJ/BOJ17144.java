import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17144 {

    static int r, c, t, up, down;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<Node> spreadQ = new ArrayDeque<>();

    static class Node {
        int x, y, num;

        public Node(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];

        boolean upFlag = false;
        boolean downFlag = false;

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < c; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1){
                    if(!upFlag){
                        up = i;
                        upFlag = true;
                    }else if(!downFlag){
                        down = i;
                        downFlag = true;
                    }
                }
            }
        }
        for(int i = 0; i < t; i++){
            addQ();
            while(!spreadQ.isEmpty()){
                Node poll = spreadQ.poll();
                if(poll.num < 5) continue;
                spread(poll);
            }
            move();
        }
        System.out.println(check());
    }


    static void spread(Node poll){
        int cnt = 0;
        int amount = poll.num;

        for(int d = 0; d < 4; d++){
            int nx = poll.x + dx[d];
            int ny = poll.y + dy[d];

            if(!isRange(nx, ny) || map[nx][ny] == -1) continue;
            map[nx][ny] += amount/5;
            cnt++;
        }
        map[poll.x][poll.y] -= (amount/5)*cnt;

    }

    static void move(){
        // Up : up to down
        for(int i = up-1; i >= 0; i--){
            if(map[i+1][0] == -1){
                map[i][0] = 0;
            }else {
                map[i+1][0] = map[i][0];
            }
        }
        // Up : right to left
        for(int i = 1; i < c; i++){
            map[0][i-1] = map[0][i];
        }
        // Up : down to up
        for(int i = 1; i <= up; i++){
            map[i-1][c-1] = map[i][c-1];
        }
        // Up : left to rigth
        for(int i = c-2; i > 0; i--){
            map[up][i+1] = map[up][i];
        }
        map[up][1] = 0;
        // Down : down to Up
        for(int i = down+1; i < r; i++){
            if(map[i-1][0] == -1){
                map[i][0] = 0;
            }else {
                map[i-1][0] = map[i][0];
            }
        }
        // Down : right to left
        for(int i = 1; i < c; i++){
            map[r-1][i-1] = map[r-1][i];
        }
        // Down : up to down
        for(int i = r-2; i >= down; i--){
            map[i+1][c-1] = map[i][c-1];
        }
        // Down : left to right
        for(int i = c-2; i > 0; i--){
            map[down][i+1] = map[down][i];
        }
        map[down][1] = 0;
    }

    static void addQ(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(map[i][j] > 0 ){
                    spreadQ.offer(new Node(i,j,map[i][j]));
                }
            }
        }
    }
    static int check(){
        int result = 0;

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(map[i][j] > 0 ){
                    result += map[i][j];
                }
            }
        }
        return result;
    }
    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= r || y >= c){
            return false;
        }

        return true;
    }
}
