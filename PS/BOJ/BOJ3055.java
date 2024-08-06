import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ3055 {

    static int r, c;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<int[]> water = new ArrayDeque<>();


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r= Integer.parseInt(st.nextToken());
        c= Integer.parseInt(st.nextToken());
        map = new char[r][c];
        boolean[][] visit = new boolean[r][c];

        int startX = 0;
        int startY = 0;

        for(int i = 0; i < r; i++){
            String str = br.readLine();
            for(int j = 0; j < c; j++){
                if(str.charAt(j) == 'S'){
                    startX = i;
                    startY = j;
                }else if(str.charAt(j) == '*'){
                    water.offer(new int[]{i,j});
                    map[i][j] = str.charAt(j);
                }else {
                    map[i][j] = str.charAt(j);
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{startX, startY});
        visit[startX][startY] = true;

        int time = 0;
        while(!q.isEmpty()) {
            time++;
            spread();
            int size = q.size();
            for(int j = 0; j < size; j++) {
                int[] poll = q.poll();

                for (int i = 0; i < 4; i++) {
                    int x = poll[0] + dx[i];
                    int y = poll[1] + dy[i];

                    if (x < 0 || y < 0 || x >= r || y >= c || visit[x][y] || map[x][y] == 'X') continue;
                    if (map[x][y] == '*') continue;
                    if (map[x][y] == 'D') {
                        System.out.println(time);
                        System.exit(0);
                    }
                    visit[x][y] = true;
                    q.offer(new int[]{x, y});
                }
            }
        }

        System.out.println("KAKTUS");
    }

    static void spread(){
        int size = water.size();

        for(int i = 0; i < size; i++){
            int[] poll = water.poll();

            for(int j = 0; j < 4; j++){
                int x = poll[0] + dx[j];
                int y = poll[1] + dy[j];

                if(x < 0 || y < 0 || x >= r || y >= c) continue;
                if(map[x][y] == 'D' || map[x][y] == 'X' || map[x][y] == '*') continue;
                map[x][y] = '*';
                water.offer(new int[]{x,y});
            }
        }
    }

}