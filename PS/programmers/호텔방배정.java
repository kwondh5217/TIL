import java.util.*;

class Solution {
    static Set<Long> set = new HashSet<>();
    static Map<Long, Long> map = new HashMap<>();
    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        
        for(int i = 0; i < room_number.length; i++) {
            long idx = room_number[i];
            if(!set.contains(idx)) {
                set.add(idx);
                answer[i] = idx;
                map.put(idx, idx + 1l);
                continue;
            }
            
            long tmp = idx;
            Queue<Long> q = new LinkedList<>();
            while(true) {
                q.offer(tmp);
                if(map.get(tmp) == null) {
                    break;
                }
                tmp = map.get(tmp);
            }
            set.add(tmp);
            
            while(!q.isEmpty()) {
                long poll = q.poll();
                map.put(poll, tmp + 1l);
            }
            answer[i] = tmp;
        }

        return answer;
    }
}