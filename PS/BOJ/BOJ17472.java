import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
// 1. 섬의 개수와 해당 섬의 원소 중 바다와 인접한 것들을 구한다.
// 2. 바다에 인접한 원소들을 하나씩 돌며 각 섬과의 최소 거리를 구한다.
// 3. 최소 스패닝 트리를 구한다.
public class BOJ17472 {

    static int n, m, result, INF = 9_999_999;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static int[] parent;
    static List<List<int[]>> adjSea = new ArrayList<>();
    static PriorityQueue<Edge> pq = new PriorityQueue<>(((o1, o2) -> o1.distance - o2.distance));

    static class Edge {
        int from;
        int to;
        int distance;
        public Edge(int from, int to, int distance){
            this.from = from;
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        result = 0;

        map = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        init();
        calculateMinCost();
        parent = new int[adjSea.size()];
        for(int i = 1; i < adjSea.size(); i++){
            parent[i] = i;
        }

        int bridge = 0;
        while(!pq.isEmpty()){
            Edge poll = pq.poll();
            int a = find(poll.from);
            int b = find(poll.to);
            if(a != b){
                union(a, b);
                bridge++;
                result += poll.distance;
            }
        }

        if(result == 0) result = -1;
        if(bridge != parent.length-2) result = -1;
        System.out.println(result);
    }
    static void union(int x, int y){
        parent[x] = y;
    }
    static int find(int x){
        if(parent[x] == x) return x;
        else return parent[x] = find(parent[x]);
    }
    static void calculateMinCost(){
        for(int i = 1; i < adjSea.size(); i++){
            for(int[] arr : adjSea.get(i)){
                for(int j = 0; j < 4; j++){
                    int x = arr[0] + dx[j];
                    int y = arr[1] + dy[j];
                    int tmp = 0;
                    while(isRange(x, y)){
                        if(map[x][y] != 0){
                            if(tmp <= 1) break;
                            pq.offer(new Edge(i, map[x][y], tmp));
                            break;
                        }
                        x += dx[j];
                        y += dy[j];
                        tmp++;
                    }
                }
            }
        }
    }
    static void init(){
        int index = 0;
        adjSea.add(new ArrayList<>());
        boolean[][] check = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                if(!check[i][j] && map[i][j] != 0){
                    adjSea.add(new ArrayList<>());
                    Queue<int[]> q = new ArrayDeque<>();
                    check[i][j] = true;
                    q.offer(new int[]{i,j});
                    index++;
                    while(!q.isEmpty()){
                        int[] poll = q.poll();

                        int nx = poll[0];
                        int ny = poll[1];
                        map[nx][ny] = index;
                        if(isAdjSea(nx, ny)){
                            adjSea.get(index).add(new int[]{nx, ny});
                        }
                        for(int k = 0; k < 4; k++){
                            int cx = nx + dx[k];
                            int cy = ny + dy[k];

                            if(!isRange(cx, cy) || check[cx][cy]) continue;
                            if(map[cx][cy] == 0) continue;

                            check[cx][cy] = true;
                            q.offer(new int[]{cx, cy});
                        }
                    }
                }
            }
        }
    }

    static boolean isRange(int x, int y){
        if(x < 0 || y < 0 || x >= n || y >= m){
            return false;
        }
        return true;
    }
    static boolean isAdjSea(int x, int y){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isRange(nx, ny) && map[nx][ny] == 0){
                return true;
            }
        }
        return false;
    }

}
