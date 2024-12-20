import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int size : tangerine) {
            map.put(size, map.getOrDefault(size, 0) + 1);
        }
        
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for(int key : map.keySet()) {
            q.offer(map.get(key));
        }
        
        int answer = 0;
        
        while(k > 0 && !q.isEmpty()) {
            k -= q.poll();
            answer++;
        }
        return answer;
    }
}
