import java.io.*;
import java.util.*;

public class BOJ16637 {
    static int n;
    static ArrayList<Integer> nums;
    static ArrayList<Character> signs;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        char[] arr = br.readLine().toCharArray();

        nums = new ArrayList<>();
        signs = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(i % 2 == 0){
                nums.add(Character.getNumericValue(arr[i]));
            }else {
                signs.add(arr[i]);
            }
        }

        dfs(0, nums.get(0));
        System.out.println(result);
    }

    static void dfs(int idx, int sum){
        if(idx == signs.size()){
            result = Math.max(result, sum);
            return;
        }

        dfs(idx + 1, calculate(sum, nums.get(idx + 1), signs.get(idx)));

        if(idx + 2 <= signs.size()){
            dfs(idx + 2, calculate(sum, calculate(nums.get(idx+1), nums.get(idx+2), signs.get(idx+1)), signs.get(idx)));
        }
    }

    static int calculate(int a, int b, char sign){
        if(sign == '+'){
            return a + b;
        }else if(sign == '-'){
            return a - b;
        }else {
            return a * b;
        }
    }
}

