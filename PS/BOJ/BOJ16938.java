import java.util.*;
import java.io.*;

class Main {

    private static int n, l, r, x;
    private static int[] arr;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        subset(0, 0, -1, Integer.MAX_VALUE, 0);

        System.out.println(answer);
    }

    private static void subset(int idx, int cnt, int max, int min, int sum) {
        if(idx == arr.length) {
            answer = check(max, min, sum) && cnt >= 2 ? answer + 1 : answer;
            return;
        }

        subset(idx + 1, cnt + 1, Math.max(arr[idx], max), Math.min(arr[idx], min), sum + arr[idx]);
        subset(idx + 1, cnt, max, min, sum);
    }

    private static boolean check(int max, int min, int sum) {
        return sum >= l && sum <= r && max - min >= x;
    }
}
