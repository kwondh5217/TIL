import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = 0;

        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = n-1;

        while(left < n && arr[left] < 1){
            if(left + 1 < n && arr[left+1] < 1){
                result += arr[left] * arr[left+1];
                left += 2;
            }else {
                result += arr[left];
                left++;
            }
        }

        while(right >= left && arr[right] > 0){
            if(right - 1 >= left && arr[right-1] > 1){
                result += arr[right] * arr[right-1];
                right -= 2;
            }else {
                result += arr[right];
                right--;
            }
        }
        System.out.println(result);
    }
}