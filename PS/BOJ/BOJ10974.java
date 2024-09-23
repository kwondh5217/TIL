import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10974 {

    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static boolean[] check;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        check = new boolean[N+1];
        arr = new int[N+1];

        solution(0);


        System.out.println(sb);
    }

    static void solution(int cnt){
        if(cnt == N){
            for(int i = 0; i < N; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i <= N; i++){
            if(check[i]){
                continue;
            }
            check[i] = true;
            arr[cnt] = i;
            solution(cnt+1);
            check[i] = false;
        }


    }

}
