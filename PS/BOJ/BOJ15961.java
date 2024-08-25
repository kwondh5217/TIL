import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ15961 {
    static int n, d, k, c, result,max;
    static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        max = 0;
        result = 0;
        sushi = new int[d+1];

        boolean flag = false;
        boolean flag2 = false;

        Queue<Integer> q = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(br.readLine());
            q2.offer(num);
            if(num == c) flag2 = true;
        }


        int index = 0;

        if(!flag2) result++;

        while(index < n+k){
            int num = q2.poll();

            if(q.size() == k) {
                int tmp = q.poll();
                sushi[tmp]--;
                if (sushi[tmp] == 0) {
                    result--;
                }
                if(sushi[c] == 0){
                    flag = false;
                }
                q2.offer(tmp);
            }
            q.offer(num);
            if(num == c){
                flag = true;
            }
            if(sushi[num] == 0){
                result++;
            }
            sushi[num]++;

            if(!flag && flag2){
                max = Math.max(max, result+1);
            }else {
                max = Math.max(max,result);
            }
            index++;
        }
        System.out.println(max);

    }
}
