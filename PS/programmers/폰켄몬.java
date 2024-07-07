import java.util.*;

class Solution {
    public int solution(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for(int i = 0; i < nums.length; i++){
            set.add(nums[i]);
        }
        
        int answer = set.size();
        
        return nums.length/2 > answer ? answer : nums.length/2;
    }
}