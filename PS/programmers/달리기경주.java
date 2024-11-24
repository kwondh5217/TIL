import java.util.*;
class Solution {
    public String[] solution(String[] players, String[] callings) {
        String[] result = new String[players.length];
        
        HashMap<String, Integer> mappedByPlayers = new HashMap<>();
        HashMap<Integer, String> mappedByRank = new HashMap<>();
        
        for(int i = 0; i < players.length; i++){
            mappedByPlayers.put(players[i], i);
            mappedByRank.put(i, players[i]);
        }
        
        for(int i = 0; i < callings.length; i++){
            int rank = mappedByPlayers.get(callings[i]);
            String player = mappedByRank.get(rank);
            
            String frontPlayer = mappedByRank.get(rank-1);
            
            mappedByPlayers.put(frontPlayer, rank);
            mappedByPlayers.put(player, rank-1);
            
            mappedByRank.put(rank, frontPlayer);
            mappedByRank.put(rank-1, player);
            
        }
        for(int i = 0; i < players.length; i++){
            result[i] = mappedByRank.get(i);
        }
        
        return result;
    }
}