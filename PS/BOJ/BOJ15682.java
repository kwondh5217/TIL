import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ15682 {
    static int n, m, cnt;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int[][] map;
    static ArrayList<CCTV> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6){
                    cctvs.add(new CCTV(i,j,map[i][j]));
                }
            }
        }
        cnt = Integer.MAX_VALUE;
        comb(0);
        System.out.println(cnt);
    }

    static void comb(int depth){
        if(depth == cctvs.size()){
            simulate();
            return;
        }

        CCTV cctv = cctvs.get(depth);
        if(cctv.num == 2) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < cctv.direction.size(); k++) {
                    int dir = (cctv.direction.get(k) + 1) % 4;
                    cctv.direction.set(k, dir);
                }
                comb(depth + 1);
            }
        }
        if(cctv.num != 2 && cctv.num != 5){
            for(int j = 0; j < 4; j++){
                for (int k = 0; k < cctv.direction.size(); k++){
                    int dir = (cctv.direction.get(k)+1)%4;
                    cctv.direction.set(k, dir);
                }
                comb(depth+1);
            }
        } else {
            comb(depth+1);
        }
    }

    static void simulate(){
        for(int i = 0; i < cctvs.size(); i++){
            for(int j = 0; j < cctvs.get(i).direction.size(); j++){
                int x = cctvs.get(i).x;
                int y = cctvs.get(i).y;

                while(true){
                    if(x < 0 || y < 0 || x >= n || y >= m) break;
                    if(map[x][y] == 0){
                        map[x][y]--;
                    }
                    if(map[x][y] == 6){
                        break;
                    }

                    x += dx[cctvs.get(i).direction.get(j)];
                    y += dy[cctvs.get(i).direction.get(j)];
                }
            }
        }

        cnt = Math.min(cnt, countBlindSpot());

        for(int i = 0; i < cctvs.size(); i++){
            for(int j = 0; j < cctvs.get(i).direction.size(); j++){
                int x = cctvs.get(i).x;
                int y = cctvs.get(i).y;

                while(true){
                    if(x < 0 || y < 0 || x >= n || y >= m) break;
                    if(map[x][y] == -1){
                        map[x][y]++;
                    }
                    if(map[x][y] == 6){
                        break;
                    }

                    x += dx[cctvs.get(i).direction.get(j)];
                    y += dy[cctvs.get(i).direction.get(j)];
                }
            }
        }
    }

    static int countBlindSpot(){
        int tmp = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] == 0) tmp++;
            }
        }
        return tmp;
    }

    static class CCTV {
        int x;
        int y;
        int num;
        ArrayList<Integer> direction;

        public CCTV(int x, int y, int num){
            this.x = x;
            this.y = y;
            this.num = num;
            this.direction = new ArrayList<>();

            switch (num){
                case 1 :
                    this.direction.add(0);
                    break;
                case 2 :
                    this.direction.add(0);
                    this.direction.add(2);
                    break;
                case 3 :
                    this.direction.add(3);
                    this.direction.add(0);
                    break;
                case 4 :
                    this.direction.add(3);
                    this.direction.add(0);
                    this.direction.add(2);
                    break;
                case 5 :
                    for(int i = 0; i < 4; i++){
                        this.direction.add(i);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
