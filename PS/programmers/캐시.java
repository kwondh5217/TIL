import java.util.*;

class Solution {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> q = new ArrayDeque<>();
        
        if(cacheSize == 0){
            answer = cities.length * 5;
            return answer;
        }

        for(int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();
            
            int size = q.size();
            String str = null;
            for(int j = 0; j < size; j++){
                String tmp = q.poll();
                if(tmp.equals(city)) str = tmp;
                else q.offer(tmp);
            }
            
            if(str != null){
                answer++;
                q.offer(str);
                continue;
            }
            
            if(size < cacheSize){
                answer+=5;
                q.offer(city);
            }else {
                answer+=5;
                q.poll();
                q.offer(city);
            }

        }

        return answer;
    }
}
