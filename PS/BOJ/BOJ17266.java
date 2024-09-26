import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17266 {
    static int n, m;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[m];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = n;
        int result = 0;

        while(left <= right){
            int mid = (left+right)/2;

            if(check(mid)){
                result = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }

        }

        System.out.println(result);
    }

    static boolean check(int height){
        int tmp = 0;

        for(int i = 0; i < m; i++){
            if(arr[i] - height <= tmp){
                tmp = arr[i] + height;
            }else {
                return false;
            }
        }

        return n - tmp <= 0;
    }

}