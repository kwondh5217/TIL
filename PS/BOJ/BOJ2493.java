import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class BOJ2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        ArrayDeque<int[]> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            int height = Integer.parseInt(st.nextToken());

            while(!stack.isEmpty()){
                if(stack.peek()[1] >= height){
                    sb.append(stack.peek()[0]+1).append(" ");
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()){
                sb.append(0).append(" ");
            }
            stack.push(new int[] {i, height});
        }
        System.out.println(sb);
    }
}
