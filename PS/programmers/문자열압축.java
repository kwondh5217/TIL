import java.util.*;

class Solution {
    public int solution(String s) {
        int minLength = s.length();
        
        for(int i = 1; i <= s.length() / 2; i++) {
            StringBuilder compressed = new StringBuilder();
            String prev = s.substring(0, i);
            int count = 1;
            for(int j = i; j <= s.length() - i; j += i) {
                String sub = s.substring(j, j + i);
                if(sub.equals(prev)) {
                    count++;
                } else {
                    if(count > 1) {
                        compressed.append(count);
                    }
                    compressed.append(prev);
                    prev = sub;
                    count = 1;
                }
            }
            if (count > 1) {
                compressed.append(count);
            }
            compressed.append(prev);
            
            if (s.length() % i != 0) {
                compressed.append(s.substring(0, s.length() % i));
            }
            
            minLength = Math.min(minLength, compressed.toString().length());
        }
        
        
        int answer = minLength;
        return answer;
    }
}
