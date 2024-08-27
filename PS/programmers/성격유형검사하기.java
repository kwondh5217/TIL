import java.util.*;

class Solution {    
    static Map<Character, Integer> map = new HashMap<>();
    
    public String solution(String[] survey, int[] choices) {
        for(int i = 0; i < choices.length; i++) {
            if(choices[i] == 4) continue;
            char key = choices[i] <= 3 ? survey[i].charAt(0) : survey[i].charAt(1);
            int score;
            if(choices[i] >= 5) {
                score = choices[i] % 4;
            } else {
                if(choices[i] == 1) score = 3;
                else if(choices[i] == 3) score = 1;
                else score = 2;
            }
            map.put(key, map.getOrDefault(key, 0) + score);
        }
        
        char[] chars = new char[4];
        chars[0] = getChar('R', 'T');
        chars[1] = getChar('C', 'F');
        chars[2] = getChar('J', 'M');
        chars[3] = getChar('A', 'N');
        String answer = String.valueOf(chars);
        return answer;
    }
    
    private static char getChar(char a, char b) {
        if(map.getOrDefault(a, 0) > map.getOrDefault(b, 0)) {
            return a;
        } else if(map.getOrDefault(a, 0) < map.getOrDefault(b, 0)) {
            return b;
        }
        return a;
    }
}
