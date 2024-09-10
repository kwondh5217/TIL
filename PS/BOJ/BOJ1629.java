import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1629 {
    static int a, b, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long recur = recur(a, b);

        System.out.println(recur);
    }

    static long recur(long x, int y){
        if(y==1) return x%c;
        int half = y/2;
        long res = recur(x,half)%c;
        res = (res*res)%c;
        return y%2==0? res%c : (res*x)%c;
    }
}
