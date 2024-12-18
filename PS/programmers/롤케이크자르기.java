import java.util.*;

class Solution {
    public int solution(int[] topping) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer> chulsu = new HashSet<>();
        
        for(int top : topping) {
            map.put(top, map.getOrDefault(top, 0) + 1);
        }
        
        int answer = 0;
        for(int top : topping) {
            int tmp = map.get(top);
            if(tmp == 1) {
                map.remove(top);
            } else {
                map.put(top, tmp - 1);
            }
            
            chulsu.add(top);
            
            if(chulsu.size() == map.keySet().size()) {
                answer++;
            }
        }
        
        return answer;
    }
}
