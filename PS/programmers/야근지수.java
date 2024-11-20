import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for(int work : works) {
            q.offer(work);
        }
        
        while(n > 0 && !q.isEmpty()) {
            int poll = q.poll();
            poll--;
            n--;
            if(poll > 0) {
                q.offer(poll);
            }
        }
        
        long answer = 0;
        while(!q.isEmpty()) {
            int poll = q.poll();
            answer += (Math.pow(poll, 2));
        }
        
        return answer;
    }
}
