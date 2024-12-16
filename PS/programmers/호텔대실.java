import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<Reservation> q = new PriorityQueue<>((a, b) -> a.start - b.start);
        PriorityQueue<Reservation> endQ = new PriorityQueue<>((a, b) -> a.end - b.end);
        
        for(String[] strs : book_time) {
            q.offer(new Reservation(strs[0], strs[1]));
        }
        
        endQ.offer(q.poll());
        int answer = 1;
        while(!q.isEmpty()) {
            Reservation poll = q.poll();
            if(endQ.peek().end > poll.start){
                answer++;
                endQ.offer(poll);
            } else {
                endQ.poll();
                endQ.offer(poll);
            }
        }
        
        return answer;
    }
    
    static class Reservation {
        private int start;
        private int end;
        
        public Reservation (String start, String end) {
            this.start = convert(start);
            this.end = convert(end) + 10;
        }
        
        private int convert(String str) {
            String[] times = str.split(":");
            return (Integer.parseInt(times[0]) * 60) + Integer.parseInt(times[1]);
        }
    }
}
