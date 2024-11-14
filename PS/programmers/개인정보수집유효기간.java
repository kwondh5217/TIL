import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int year = Integer.parseInt(today.substring(0,4));
        int month = Integer.parseInt(today.substring(5,7));
        int day = Integer.parseInt(today.substring(8));
        
        HashMap<String, Integer> set = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        
        StringTokenizer st;
        for(String str : terms){
            st = new StringTokenizer(str, " ");
            set.put(st.nextToken(), Integer.parseInt(st.nextToken()));
        }
        
        for(int i = 0; i < privacies.length; i++){
            String str = privacies[i];
            st = new StringTokenizer(str, " ");
            String date = st.nextToken();
            String grade = st.nextToken();
            int nYear = (year - Integer.parseInt(date.substring(0,4))) * 28 * 12;
            int nMonth = (month - Integer.parseInt(date.substring(5,7))) * 28;
            int nDay = (day - Integer.parseInt(date.substring(8)));
            
            
            int type = set.get(grade)*28;
            
            
            int nNum = nYear + nMonth + nDay;
            
            if(nNum >= type){
                q.add(i+1);
            }
            
        }
        
        int size = q.size();
        
        int[] answer = new int[size];
        
        for(int i = 0; i < size; i++){
            answer[i] = q.poll();
        }
        
        return answer;
    }
}
