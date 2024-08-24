import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ17952 {
    static int n, result;
    static PriorityQueue<int[]> q = new PriorityQueue<>((e1, e2) -> e2[0] - e1[0]);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            if(a == 0 && q.isEmpty()) continue;
            if(a == 0){
                int[] poll = q.poll();
                if(poll[2]-1 == 0){
                    result += poll[1];
                }else {
                    poll[2]--;
                    q.add(poll);
                }
            }else {
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken()) - 1;

                if(c == 0){
                    result += b;
                }else {
                    q.add(new int[]{i,b,c});
                }
            }
        }

        System.out.println(result);
    }
}
