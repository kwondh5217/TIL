import java.util.*;

class Solution {
    static int max = 0;
    
    public int solution(int[] diffs, int[] times, long limit) {
        for(int num : diffs) {
            max = Math.max(num, max);
        }
        
        int left = 1;
        int right = max;
        int answer = Integer.MAX_VALUE;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            
            boolean flag = true;
            
            int prev = 0;
            long curLimit = limit;
            for(int i = 0; i < diffs.length; i++) {
                if(mid >= diffs[i]) {
                    curLimit -= times[i];
                } else {
                    int tmp = ((prev + times[i]) * (diffs[i] - mid)) + times[i];
                    curLimit -= tmp;
                }
                prev = times[i];
                
                if(curLimit < 0) {
                    flag = false;
                    break;
                }
            }
            
            if(flag) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
}
