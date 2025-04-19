import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, cnt;
    static int[] arr;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        check = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        simulate(0, 500);

        System.out.println(cnt);
    }

    private static void simulate(int idx, int cur) {
        if (idx == N) {
            cnt++;
            return;
        }

        if (cur < 500) {
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                check[i] = true;
                simulate(idx + 1, cur + arr[i] - K);
                check[i] = false;
            }
        }
    }
}
