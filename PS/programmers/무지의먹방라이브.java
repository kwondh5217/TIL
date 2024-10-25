import java.util.*;
class Solution {
    static ArrayDeque<int[]> deque = new ArrayDeque<>();
    public int solution(int[] food_times, long k) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        for(int i = 0; i < food_times.length; i++) {
            q.offer(new int[]{food_times[i], i + 1});
        }
        
        long size = q.size();
        long prev = 0;
        while(!q.isEmpty()) {
            int[] peek = q.peek();
            long time = (peek[0] - prev) * size;
            
            if(k >= time) {
                int[] poll = q.poll();
                k -= time;
                prev = poll[0];
                size--;
            } else {
                k %= size;
                List<int[]> list = new ArrayList<>(q);
                list.sort(Comparator.comparingInt(a -> a[1]));
                return list.get((int) k)[1];
            }
        }
        
        return -1;
    }
}