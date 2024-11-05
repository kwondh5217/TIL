import java.util.*;

class Solution {
    public int[] solution(String s) {
        int round = 0;
        int cnt = 0;
        while(true) {
            int before = s.length();
            s = s.replaceAll("0", "");
            int after = s.length();
            
            cnt += before - after;
            round++;
            
            s = Integer.toBinaryString(after);
            if(s.equals("1")) {
                break;
            }
        }
        int[] answer = {round, cnt};
        return answer;
    }
}
