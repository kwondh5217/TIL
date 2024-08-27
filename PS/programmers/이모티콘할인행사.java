import java.util.*;

class Solution {
    
    static double[] discounts = {0.1, 0.2, 0.3, 0.4};
    static double[] emoticonsDiscounts;
    static int[] emoticons;
    static int[][] users;
    static int maxCnt, maxResult;
    public int[] solution(int[][] usersInputs, int[] emoticonsInputs) {
        emoticons = emoticonsInputs;
        users = usersInputs;
        emoticonsDiscounts = new double[emoticons.length];
        // 중복 조합으로 이모티콘에 할인률 적용
        comb(0);
        
        int[] answer = {maxCnt, maxResult};
        return answer;
    }
    
    private static void simulate() {
        int[] usersTemp = new int[users.length];
        int cnt = 0;
        int result = 0;
        
        for(int i = 0; i < users.length; i++) {
            for(int j = 0; j < emoticonsDiscounts.length; j++) {
                // 이모티콘 할인률이 유저의 할인률 보다 높다면 구매
                if(users[i][0] <= emoticonsDiscounts[j] * 100) {
                    usersTemp[i] += emoticons[j] * (1 - emoticonsDiscounts[j]);
                }
            }
        }
        
        for(int i = 0; i < usersTemp.length; i++) {
            // 현재 구매액이 기준보다 높다면 가입
            if(usersTemp[i] >= users[i][1]) {
                cnt++;
                continue;
            }
            // 아니라면 판매액을 증가
            result += usersTemp[i];
        }
        
        
        if(cnt > maxCnt) {
            maxCnt = cnt;
            maxResult = result;
        } 
        else if(cnt == maxCnt) {
            maxResult = Math.max(maxResult, result);
        }
    }
    
    private static void comb(int cnt) {
        if(cnt == emoticonsDiscounts.length) {
            simulate();
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            emoticonsDiscounts[cnt] = discounts[i];
            comb(cnt + 1);
        }
    }
}
