import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ9205 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for(int test = 0; test < t; test++){
            int n = Integer.parseInt(br.readLine());

            int[][] map = new int[n+2][2];
            for(int i = 0; i < n+2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[i][0] = x;
                map[i][1] = y;
            }

            boolean[][] isPossible = new boolean[n+2][n+2];

            for(int i = 0; i < n+2; i++){
                for(int j = 0; j < n+2; j++){
                    for(int k = 0; k < n+2; k++){
                        if(j == k) continue;
                        int jk = Math.abs(Math.abs(map[j][0] - map[k][0]) + Math.abs(map[j][1] - map[k][1]));
                        if(jk <= 1000) {
                            isPossible[j][k] = true;
                        }
                        if(i != 0) {
                            int ji = Math.abs(Math.abs(map[j][0] - map[i][0]) + Math.abs(map[j][1] - map[i][1]));
                            int ik = Math.abs(Math.abs(map[i][0] - map[k][0]) + Math.abs(map[i][1] - map[k][1]));
                            if(ji <= 1000 && ik <= 1000){
                                isPossible[j][i] = true;
                                isPossible[i][k] = true;
                            }
                        }

                    }
                }
            }

            boolean flag = false;
            boolean[] visit = new boolean[n+2];
            Queue<Integer> q = new ArrayDeque<>();
            visit[0] = true;
            q.offer(0);

            while(!q.isEmpty()){
                int poll = q.poll();

                if(poll == n+1){
                    flag = true;
                    break;
                }

                for(int i = 0; i < n+2; i++){
                    if(i == poll || !isPossible[poll][i] || visit[i]) continue;
                    visit[i] = true;
                    q.offer(i);
                }
            }

            if(flag){
                sb.append("happy").append("\n");
            }else {
                sb.append("sad").append("\n");
            }
        }
        System.out.println(sb);
    }


}