import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long left = 0;
        long right = times[times.length-1] * (long) n;
        long answer = right;
        
        while(left <= right){
            long mid = (left + right) / 2;
            
            long people = 0;
            for(int time : times) {
                people += mid / time;
            }
            
            if(people < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}
