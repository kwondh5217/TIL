import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>();
        
        for(int i = 0; i < phone_book.length; i++){
            set.add(phone_book[i]);
        }
        
        boolean answer = true;
        
        for(String str : set) {
            for(int i = 1; i < str.length(); i++){
                String subString = str.substring(0, i);
                if(set.contains(subString)){
                    answer = false;
                    break;
                }
            }
        }
        return answer;
    }
}