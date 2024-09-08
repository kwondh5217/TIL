import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
    static int n;
    static int[][] map;
    static int white, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        white = 0;
        blue = 0;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(n,0, 0);

        System.out.println(white);
        System.out.println(blue);
    }

    static void recur(int size, int x, int y){

//        if(size/2 == 0){
//            if(map[x][y] == 0){
//                white++;
//            }else {
//                blue++;
//            }
//            return;
//        }

        int w = 0;
        int b = 0;
        for(int i = x; i < x + size; i++){
            for(int j = y; j < y + size; j++){
                if(map[i][j] == 0){
                    w++;
                }else {
                    b++;
                }
            }
        }

        if(w == 0 && b > 0){
            blue++;
            return;
        }
        if(b == 0 && w > 0){
            white++;
            return;
        }

        size /= 2;

        recur(size, x, y);
        recur(size, x, y+size);
        recur(size, x+size, y);
        recur(size, x+size, y+size);

    }



}
