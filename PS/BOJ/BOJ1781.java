import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void BOJ1781(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int result = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>((e1, e2) -> e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0]);
        PriorityQueue<int[]> q2 = new PriorityQueue<>((e1, e2) -> e1[1] - e2[1]);
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            q.offer(new int[]{a,b});
        }

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int deadline = poll[0];
            int cntOfCup = poll[1];

            if(q2.isEmpty()){
                q2.offer(poll);
            }else if(q2.size() < deadline){
                q2.offer(poll);
            }else if(q2.peek()[1] < cntOfCup){
                q2.poll();
                q2.offer(poll);
            }
        }

        while(!q2.isEmpty()){
            result += q2.poll()[1];
        }

        System.out.println(result);
    }
}
