class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int zeroCnt = 0;
        
        for(int num : lottos){
            if(num == 0){
                zeroCnt++;
            }
        }
        int win = 0;
        
        for(int num : lottos){
            if(num == 0){
                continue;
            }else {
                for(int winNum : win_nums){
                    if(num == winNum){
                        win++;
                        break;
                    }
                }
            }
        }
        
        
        int a = 7 - (win + zeroCnt);
        int b = 7 - win;
        if(a == 7) a = 6;
        if(b == 7) b = 6;
        
        int[] answer = {a,b};
        
        
        
        return answer;
    }
}
