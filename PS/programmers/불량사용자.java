import java.util.*;

class Solution {
    static List<List<String>> candidates = new ArrayList<>();
    static Set<Set<String>> uniqueCombinations = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        
        for (String banned : banned_id) {
            List<String> matchList = new ArrayList<>();
            for (String user : user_id) {
                if (isMatch(user, banned)) {
                    matchList.add(user);
                }
            }
            candidates.add(matchList);
        }
        
        comb(0, new HashSet<>());
        
        return uniqueCombinations.size();
    }
    
    private boolean isMatch(String user, String banned) {
        if (user.length() != banned.length()) return false;
        
        for (int i = 0; i < banned.length(); i++) {
            if (banned.charAt(i) == '*') continue;
            if (banned.charAt(i) != user.charAt(i)) return false;
        }
        return true;
    }
    
    private void comb(int idx, Set<String> selected) {
        if (idx == candidates.size()) {
            uniqueCombinations.add(new HashSet<>(selected));
            return;
        }
        
        for (String user : candidates.get(idx)) {
            if (!selected.contains(user)) {
                selected.add(user);
                comb(idx + 1, selected);
                selected.remove(user);
            }
        }
    }
}
