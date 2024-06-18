import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int answer = 1;
        int high = targets[0][1];
        
        for(int i = 1; i < targets.length; i++){
            if(targets[i][0] >= high){
                high = targets[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}