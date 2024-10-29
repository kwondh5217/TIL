import java.util.*;

class Solution {
    static Set<String> set = new HashSet<>();
    static String[] input;
    static class Result {
        boolean success;
        int[] answer;
        
        public Result (boolean success, int[] answer) {
            this.success = success;
            this.answer = answer;
        }
    }
    public int[] solution(String[] gems) {
        input = gems;
        for(int i = 0; i < gems.length; i++) {
            set.add(gems[i]);
        }
        
        if(set.size() == 1) {
            return new int[]{1, 1};
        }
        
        int left = 0;
        int right = gems.length;
        int size = Integer.MAX_VALUE;
        int[] answer = new int[2];
        while(left <= right) {
            int mid = (left + right) / 2;
            
            Result result = check(mid);
            
            if(result.success) {
                if(mid <= size) {
                    answer[0] = result.answer[0] + 1;
                    answer[1] = result.answer[1] + 1;
                    size = mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
    
    private Result check(int size) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> tmp = new HashSet<>();
        for(int i = 0; i < size; i++) {
            map.put(input[i], map.getOrDefault(input[i], 0) + 1);
            tmp.add(input[i]);
        }
        
        if(tmp.size() == set.size()) {
            return new Result(true, new int[]{0, size - 1});
        }
        
        for(int i = size; i < input.length; i++) {
            map.put(input[i - size], map.getOrDefault(input[i - size], 1) - 1);
            
            if(map.getOrDefault(input[i - size], 0) == 0) {
                tmp.remove(input[i - size]);
            }
            
            map.put(input[i], map.getOrDefault(input[i], 0) + 1);
            tmp.add(input[i]);
            
            if(tmp.size() == set.size()) {
                return new Result(true, new int[]{i - size + 1, i});
            }
        }
        
        return new Result(false, new int[]{-1, -1});
    }
}
