import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[24];
        for(int i = 0; i < players.length; i++) {
            int count = players[i] / m;
            if(count == 0) continue;
            if(servers[i] >= count) continue;
            int needServers = count - servers[i];
            for(int j = i; j < i + k && j < 24; j++) {
                servers[j] += needServers;
            }
            answer += needServers;
        }
        return answer;
    }
}
