import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1194 {
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][][] visited;
    static Node start;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[64][N][M];

        for(int i = 0; i < N; i++){
            String str = br.readLine();
            for(int j = 0; j < M; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == '0'){
                    start = new Node(i, j, 0, 0);
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs(){
        Queue<Node> q = new LinkedList<>();

        q.add(start);
        visited[0][start.x][start.y] = true;

        while(!q.isEmpty()){
            Node poll = q.poll();
            if(map[poll.x][poll.y] == '1'){
                return poll.cnt;
            }

            for(int i = 0; i < 4; i++){
                int nx = poll.x + dx[i];
                int ny = poll.y + dy[i];
                int key = poll.key;

                if(!isPossible(nx, ny, key)) {
                    continue;
                }

                if(map[nx][ny] >= 'a' && map[nx][ny] <= 'f') {
                    int next = 1 << (map[nx][ny] - 'a');
                    next = key | next;
                    visited[next][nx][ny] = true;
                    q.add(new Node(nx, ny, poll.cnt + 1, next));
                } else if(map[nx][ny] >= 'A' && map[nx][ny] <= 'F') {
                    if ((key & 1 << (map[nx][ny] - 'A')) == (int) Math.pow(2, map[nx][ny] - 'A')) {
                        visited[key][nx][ny] = true;
                        q.add(new Node(nx, ny, poll.cnt + 1, key));
                    }
                }else {
                    visited[key][nx][ny] = true;
                    q.add(new Node(nx, ny, poll.cnt + 1, key));
                }
            }
        }

        return -1;
    }
    static boolean isPossible(int x, int y, int key){
        if(x < 0 || y < 0 || x >= N || y >= M) return false;
        if(map[x][y] == '#' || visited[key][x][y]) return false;

        return true;
    }
    static class Node {
        int x;
        int y;
        int cnt;
        int key;

        public Node(int x, int y, int cnt, int key){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }

}
