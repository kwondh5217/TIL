import java.io.*;
import java.util.*;

public class BOJ6603 {
    static int N;
    static int[] arr = new int[6];
    static int[] nums;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            
            nums = new int[N];
            
        
            
            if(N == 0){
                break;
            }
            
            for(int i = 0; i < N; i++) {
            	nums[i] = Integer.parseInt(st.nextToken());
            }
          
            
            comb(0, 0);
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    
    static void comb(int depth, int index){
        if(depth == 6){
            for(int val : arr){
                sb.append(val).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        
        if(index == N) {
        	return;
        }
        
        arr[depth] = nums[index];
        
        comb(depth + 1, index + 1);
        comb(depth, index + 1);
    }
}
