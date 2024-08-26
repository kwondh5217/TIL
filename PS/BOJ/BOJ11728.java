import java.util.*;
import java.io.*;

public class BOJ11728 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            q.offer(Integer.parseInt(st.nextToken()));
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++) {
            q.offer(Integer.parseInt(st.nextToken()));
        }
        
        while(!q.isEmpty()) {
            sb.append(q.poll() + " ");
        }
        System.out.println(sb);
    }
}
