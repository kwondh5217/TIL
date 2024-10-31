import java.util.*;

class Solution {
    static List<List<Integer>> list = new ArrayList<>();
    static Set<Integer> prev = new HashSet<>();
    static Map<Integer, Integer> keys = new HashMap<>();
    static Map<Integer, Integer> values = new HashMap<>();
    static boolean[] visit;
    static boolean[] check;
    public boolean solution(int n, int[][] path, int[][] order) {
        for(int i = 0; i < order.length; i++) {
            values.put(order[i][1], order[i][0]);
            keys.put(order[i][0], order[i][1]);
        }
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < path.length; i++) {
            int a = path[i][0];
            int b = path[i][1];
            
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
        visit = new boolean[n];
        check = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        if(!values.containsKey(0)) {
            q.offer(0);
            visit[0] = true;
            prev.add(0);
        }
        
        while(!q.isEmpty()) {
            int idx = q.poll();
            
            if(prev.size() == n) {
                return true;
            }
            
            for(int i : list.get(idx)) {
                if(values.containsKey(i) && !prev.contains(values.get(i))) {
                    check[i] = true;
                    continue;
                }
                if(visit[i]) continue;
                
                int key = keys.getOrDefault(i, -1);
                if(key != -1 && values.containsKey(key) && check[key]) {
                    visit[key] = true;
                    q.offer(key);
                    prev.add(key);
                }
                visit[i] = true;
                q.offer(i);
                prev.add(i);
            }
        }
        
        return false;
    }
}
