import java.util.*;

class Solution {
    
    public int solution(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("zero", 0);
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);
        map.put("five", 5);
        map.put("six", 6);
        map.put("seven", 7);
        map.put("eight", 8);
        map.put("nine", 9);
        
        
        StringBuilder sb = new StringBuilder();
        StringBuilder key = new StringBuilder();
        int index = 0;
        String str = "";
        while(index < s.length()){
            
            if(s.charAt(index) >= 48 && s.charAt(index) <= 58){
                sb.append(s.charAt(index));
            }else {
                str = str + s.charAt(index);
                if(map.containsKey(str)){
                    sb.append(map.get(str));
                    str = "";
                }
            }
            index++;
        }
        
        int answer = Integer.parseInt(sb.toString());
        return answer;
    }
}