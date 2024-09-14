import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class BOJ1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            q.add(i);
        }
        int cnt = 1;
        sb.append("<");
        while(!q.isEmpty()){
            Integer poll = q.poll();
            if(cnt == k){
                if(q.isEmpty()){
                    sb.append(poll);
                }else {
                    sb.append(poll).append(", ");
                }
                cnt = 0;
            }else {
                q.add(poll);
            }

            cnt++;
        }
        sb.append(">");

        System.out.println(sb);

    }
}
