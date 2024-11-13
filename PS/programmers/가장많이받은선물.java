import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        Map<String, Integer> hashMap = new HashMap<>();
        for(int i = 0; i < friends.length; i++) {
            hashMap.put(friends[i], i);
        }

        int[][] map = new int[friends.length][friends.length];
        int[] nums = new int[friends.length];

        for(int i = 0; i < gifts.length; i++){
            StringTokenizer st = new StringTokenizer(gifts[i]);
            int from = hashMap.get(st.nextToken());
            int to = hashMap.get(st.nextToken());

            nums[from]++;
            nums[to]--;
            map[from][to]++;
        }

        for(int i = 0; i < friends.length; i++){
            int cnt = 0;
            for(int j = 0; j < friends.length; j++){
                if(i == j) continue;
                
                if(map[i][j] > map[j][i]) cnt++;
                else if(map[i][j] == map[j][i] && nums[i] > nums[j]) cnt++;
            }
            answer = Math.max(answer, cnt);
        }

        return answer;
    }
}
