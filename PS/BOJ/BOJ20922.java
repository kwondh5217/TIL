import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ20922 {


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] cnt = new int[100001];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 1;
        int length = 1;
        cnt[arr[left]] = 1;
        while(right < n && left < n){
            length = Math.max(length, right-left);

            // 오른쪽으로 갈 수 있다면 진행
            if(cnt[arr[right]] < k){
                cnt[arr[right]]++;
                right++;
                continue;
            }

            // 오른쪽으로 못간다면 왼쪽을 당기기
            cnt[arr[left]]--;
            left++;
        }
        length = Math.max(length, right - left);

        System.out.println(length);
    }
}
