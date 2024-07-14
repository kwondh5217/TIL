import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1446 {

    static int n, d;
    static int result = Integer.MAX_VALUE;
    static int[][] arr;


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        arr = new int[n][3];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (o1, o2) -> o1[0] - o2[0]);
        solution(0, 0);

        System.out.println(result);
    }


    static void solution(int start, int cur){
        int tmp = 0;

        while(start < d){

            for(int i = 0; i < n; i++){
                if(arr[i][0] == start){
                    solution(arr[i][1], cur+tmp+arr[i][2]);
                }
            }
            tmp++;
            start++;
        }

        if(start == d) {
            result = Math.min(result, cur+tmp);
        }
    }
}
