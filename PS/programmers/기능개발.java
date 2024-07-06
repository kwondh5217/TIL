import java.util.*;

class Solution {
    
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i < progresses.length; i++) {
            int percentage = 100 - progresses[i];
            int days = percentage / speeds[i];
            
            days = percentage % speeds[i] == 0 ? days : days + 1;
            q.offer(days);
        }
        
        int maxDay = q.poll();
        int count = 1;
        
        while(!q.isEmpty()) {
            int day = q.poll();
            
            if(maxDay < day) {
                list.add(count);
                maxDay = day;
                count = 1;
            } else {
                count++;
            }
        }
        
        list.add(count);
        
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}