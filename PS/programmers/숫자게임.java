import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(B);
        
        Map<Integer, Boolean> map = new HashMap<>();
        int result = 0;

        for(int i = 0; i < A.length; i++) {
            int left = 0;
            int right = A.length - 1;
            int answer = Integer.MAX_VALUE;

            while(left <= right) {
                int mid = left + (right - left) / 2;
                
                if(B[mid] > A[i] && !map.getOrDefault(mid, false)) {
                    answer = Math.min(answer, mid);
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if(answer != Integer.MAX_VALUE && !map.getOrDefault(answer, false)) {
                map.put(answer, true);
                result++;
            }
        }
        
        
        return result;
    }
}
