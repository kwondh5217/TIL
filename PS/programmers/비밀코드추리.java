import java.util.*;

class Solution {
    
    static int cnt = 0;
    static int N;
    static Set<Integer>[] sets;
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        sets = new HashSet[q.length];
        for(int i = 0; i < q.length; i++) {
            sets[i] = new HashSet<>();
            for(int num : q[i]) {
                sets[i].add(num);
            }
        }
        
        comb(1, new ArrayList<Integer>(), 0, ans);
        
        return cnt;
    }
    
    private static void comb(int idx, List<Integer> list, int depth, int[] ans) {
        if(depth == 5) {
            if(checkComb(list, ans)) {
                cnt++;
            }
            return;
        }
        
        for(int i = idx; i <= N; i++) {
            list.add(i);
            comb(i + 1, list, depth + 1, ans);
            list.remove(list.size() - 1);
        }
    }
    private static boolean checkComb(List<Integer> list, int[] ans) {
        for(int i = 0; i < sets.length; i++) {
            int tmp = 0;
            for(int num : list) {
                if(sets[i].contains(num)) {
                    tmp++;
                }
                if(tmp > ans[i]) {
                    return false;
                }
            }
            if(tmp != ans[i]) {
                return false;
            }
        }
        return true;
    }
    
}
