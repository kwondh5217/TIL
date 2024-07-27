import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ21921 {
    static int n, x;
    static int[] input;
    static Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        input = new int[n];
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        int max = 0;
        int maxCnt = 0;
        int cur = 0;
        for(int i = 0; i < n; i++){
            input[i] = Integer.parseInt(st.nextToken());
            if(cnt < x){
                cnt++;
                cur+=input[i];
            }else {
                cur -= input[i-x];
                cur += input[i];
            }
            if(max <= cur){
                if(max == cur){
                    maxCnt++;
                }else {
                    max = cur;
                    maxCnt = 1;
                }
            }
        }
        if(max == 0){
            System.out.println("SAD");
        }else {
            System.out.println(max);
            System.out.println(maxCnt);
        }
    }
}
