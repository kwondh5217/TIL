import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if(s / n == 0) {
            return new int[]{-1};
        }
        
        int cnt = s / n;
        int mod = s % n;
        
        int[] answer = new int[n];
        for(int i = 0; i < n; i++) {
            if(mod > 0) {
                answer[i] = cnt + 1;
                mod--;
            } else {
                answer[i] = cnt;
            }
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
}
