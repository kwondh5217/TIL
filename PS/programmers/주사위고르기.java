import java.util.*;

class Solution {
    
    private static boolean[] check;
    private static int[][] dice;
    private int maxWin;
    private static List<Integer> maxList;
    
    public int[] solution(int[][] input) {
        maxWin = 0;
        
        // 주사위를 반으로 나누기
        dice = input;
        check = new boolean[dice.length];
        comb(0, 0, dice.length);

        int[] answer = new int[dice.length / 2];
        for(int i = 0; i < maxList.size(); i++){
            answer[i] = maxList.get(i) + 1;
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    private void comb(int index, int count, int limit) {
        // 조합을 구했다면, 구해진 조합으로 주사위 굴리기
        if(count == limit / 2) {
            simulate();
            return;
        }
        
        for(int i = index; i < limit; i++){
            check[i] = true;
            comb(i + 1, count + 1, limit);
            check[i] = false;
        }
    }
    
    private void simulate() {
        List<Integer> aGroups = new ArrayList<>();
        List<Integer> bGroups = new ArrayList<>();
        
        for(int i = 0; i < dice.length; i++) {
            if(check[i]){
                aGroups.add(i);
            } else {
                bGroups.add(i);
            }
        }
        
        List<Integer> aNums = new ArrayList<>();
        List<Integer> bNums = new ArrayList<>();
        
        diceComb(0, 0, aGroups, aNums);
        diceComb(0, 0, bGroups, bNums);
        
        Collections.sort(bNums);
        
        int win = 0;
        
        for(int i = 0; i < aNums.size(); i++){
                     int target = aNums.get(i);
                     
                     int start = 0;
                     int end   = aNums.size();
                     while (start < end){
                         int mid = (start + end) / 2;
                         if(target <= bNums.get(mid)){
                             end = mid;
                         }
                         else start = mid + 1;
                     }
                     win += end; 
                 }
        
        if(win > maxWin) {
            maxWin = win;
            maxList = aGroups;
        }
    }
    
    private void diceComb(int index, int sum, List<Integer> groups, List<Integer> nums) {
        if(index == groups.size()){
            nums.add(sum);
            return;
        }
        
        int diceIndex = groups.get(index);
        
        for(int i = 0; i < 6; i++) {
            diceComb(index + 1, sum + dice[diceIndex][i], groups, nums);
        }
    }
}
