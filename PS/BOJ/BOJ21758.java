import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        long[] sum = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = arr[i] + (i > 0 ? sum[i - 1] : 0);
        }

        long max = 0;

        for (int i = 1; i < n - 1; i++) {
            long bee1 = sum[n - 1] - arr[0] - arr[i];
            long bee2 = sum[n - 1] - sum[i];
            max = Math.max(max, bee1 + bee2);
        }

        for (int i = 1; i < n - 1; i++) {
            long bee1 = sum[i - 1];
            long bee2 = sum[n - 1] - arr[n - 1] - arr[i];
            max = Math.max(max, bee1 + bee2);
        }

        for (int i = 1; i < n - 1; i++) {
            long bee1 = sum[i] - arr[0];
            long bee2 = sum[n - 1] - sum[i - 1] - arr[n-1];
            max = Math.max(max, bee1 + bee2);
        }

        System.out.println(max);
    }
}
