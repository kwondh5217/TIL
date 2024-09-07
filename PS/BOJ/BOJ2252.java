import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ2252 {
    static int n, m;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        nums = new int[n+1];
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> zeros = new ArrayList<>();

        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            nums[b]++;
        }

        for(int i = 1; i <= n; i++){
            if(nums[i] == 0){
                zeros.add(i);
            }
        }

        Queue<Integer> q = new ArrayDeque<>();

        q.add(zeros.get(0));
        zeros.remove(0);

        while(!q.isEmpty()){
            int poll = q.poll();

            sb.append(poll).append(" ");

            for(int num : list.get(poll)){
                nums[num]--;
                if(nums[num] == 0) {
                    q.offer(num);
                }
            }

            if(q.isEmpty() && !zeros.isEmpty()){
                q.offer(zeros.get(0));
                zeros.remove(0);
            }
        }

        System.out.println(sb);
    }
}
