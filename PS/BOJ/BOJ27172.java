import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        boolean[] cards = new boolean[1000001];
        int[] score = new int[1000001];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            cards[nums[i]] = true;
        }

        for(int i = 0; i < n; i++) {
            for(int j = nums[i] * 2; j < 1000001; j += nums[i]) {
                if(cards[j]) {
                    score[nums[i]]++;
                    score[j]--;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            sb.append(score[nums[i]]).append(" ");
        }

        System.out.println(sb);
    }
}
