import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        for(int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            if(x == 0) {
                if(q.isEmpty()) {
                    sb.append(0).append("\n");
                } else {
                    sb.append(q.poll()[1]).append("\n");
                }
            } else {
                q.offer(new int[]{Math.abs(x), x});
            }
        }

        System.out.println(sb.toString());

    }
}
