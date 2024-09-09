import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3109 {

    static int r, c;
    static char[][] map;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];

        for(int i = 0; i < r; i++){
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        for(int i = 0; i < r; i++){
            if(dfs(i, 0)){
                cnt++;
            }
        }

        System.out.println(cnt);


    }

    static boolean dfs(int x, int y){
        map[x][y] = '-';

        if(y == c-1) return true;

        if(x > 0 && map[x-1][y+1] == '.'){
            if(dfs(x-1, y+1)){
                return true;
            }
        }

        if(map[x][y+1] == '.'){
            if(dfs(x, y+1)){
                return true;
            }
        }

        if(x+1 <= r-1 && map[x+1][y+1] == '.'){
            if(dfs(x+1, y+1)){
                return true;
            }
        }

        return false;
    }
}
