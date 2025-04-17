import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            int answer = 0;
            int tmp = 0;

            for (int i = 0; i < n; i++) {
                if (tmp + arr[i][1] > w) {
                    answer += arr[i][0] * 2;
                    tmp = 0;
                }

                tmp += arr[i][1];

                if (tmp == w) {
                    answer += arr[i][0] * 2;
                    tmp = 0;
                }
            }

            if (tmp > 0) {
                answer += arr[n - 1][0] * 2;
            }

            sb.append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
