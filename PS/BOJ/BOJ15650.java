import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class BOJ15650 {

    static int N, M;
    static int[] arr;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];

        recur(0, 1);

        System.out.println(sb);

    }
    
    static void recur(int depth, int index){
        if (depth == M) {
            for(int i : arr){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = index; i <= N; i++) {
            arr[depth] = i;
            recur(depth+1, i+1);
        }
    }
}