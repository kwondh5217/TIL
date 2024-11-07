import java.util.*;
class Solution {
    public int[] solution(String s) {
        String[] strs = s.split("}");
        PriorityQueue<String> q = new PriorityQueue<>((a, b) -> {
            return a.length() - b.length();
        });
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        
        for(String str : strs) {
            str = str.replace("{", "");
            q.offer(str);
        }
        
        while(!q.isEmpty()) {
            String str = q.poll();
            
            String[] tmp = str.split(",");
            for(String t : tmp) {
                if(t.isEmpty()) continue;
                int num = Integer.parseInt(t);
                if(!set.contains(num)) {
                    set.add(num);
                    list.add(num);
                }
            }
        }

        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}