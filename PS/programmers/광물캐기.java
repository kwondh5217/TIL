import java.util.*;

class Solution {
    static int minFatigue = Integer.MAX_VALUE;
    static int[][] fatigue = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    static String[] mineralTypes = {"diamond", "iron", "stone"};
    
    public int solution(int[] picks, String[] minerals) {
        int totalPicks = Arrays.stream(picks).sum();
        int maxMinerals = Math.min(totalPicks * 5, minerals.length);
        
        backtrack(picks, minerals, 0, 0, maxMinerals);
        
        return minFatigue;
    }

    private void backtrack(int[] picks, String[] minerals, int index, int fatigueSum, int maxMinerals) {
        if (fatigueSum >= minFatigue) {
            return;
        }

        if (index >= maxMinerals || Arrays.stream(picks).sum() == 0) {
            minFatigue = Math.min(minFatigue, fatigueSum);
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                int fatigueCost = calculateFatigue(i, minerals, index, maxMinerals);
                backtrack(picks, minerals, index + 5, fatigueSum + fatigueCost, maxMinerals);
                picks[i]++;
            }
        }
    }

    private int calculateFatigue(int pickType, String[] minerals, int start, int maxMinerals) {
        int fatigueCost = 0;

        for (int i = start; i < Math.min(start + 5, maxMinerals); i++) {
            for (int j = 0; j < 3; j++) {
                if (minerals[i].equals(mineralTypes[j])) {
                    fatigueCost += fatigue[pickType][j];
                    break;
                }
            }
        }

        return fatigueCost;
    }
}
