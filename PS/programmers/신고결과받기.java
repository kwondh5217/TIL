import java.util.*;

class Solution {
    static Map<String, Set<String>> maps = new HashMap<>();
    static Map<String, Integer> results = new HashMap<>();
    public int[] solution(String[] id_list, String[] report, int k) {
        for(int i = 0; i < id_list.length; i++) {
            maps.put(id_list[i], new HashSet<String>());
            results.put(id_list[i], 0);
        }
        
        for(int i = 0; i < report.length; i++) {
            StringTokenizer st = new StringTokenizer(report[i], " ");
            String from = st.nextToken();
            String to = st.nextToken();
            Set<String> set = maps.get(to);
            set.add(from);
        }
        
        for(String key : maps.keySet()) {
            Set<String> set = maps.get(key);
            if(set.size() >= k) {
                for(String id : set) {
                    results.put(id, results.get(id) + 1);
                }
            }
        }
        
        int[] answer = new int[id_list.length];
        for(int i = 0; i < id_list.length; i++) {
            answer[i] = results.get(id_list[i]);
        }
        return answer;
    }
}
