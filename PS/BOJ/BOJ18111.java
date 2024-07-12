import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18111 {

    static int N, M, B;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j =0 ; j < M; j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
        }
        int time = Integer.MAX_VALUE;
        int height = 0;
        for(int h = min; h <= max; h++) {
            int tmpTime = 0;
            int tmpBlock = B;
            int tmpCnt = 0;

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] < h) {
                        tmpCnt += h - map[i][j];
                        continue;
                    }
                    int blockCnt = map[i][j] - h;
                    tmpTime += blockCnt*2;
                    tmpBlock += blockCnt;
                }
            }

            if(tmpTime > time || tmpCnt > tmpBlock) continue;

            tmpTime += tmpCnt;

            if(tmpTime < time){
                time = tmpTime;
                height = h;
            }else if(tmpTime == time){
                height = Math.max(height, h);
            }
        }
        System.out.println(time + " " + height);
    }

}
