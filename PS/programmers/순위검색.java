import java.util.*;

class Solution {
    static Map<String, List<Node>> map = new HashMap<>();
    public int[] solution(String[] info, String[] query) {
        for(int i = 0; i < info.length; i++) {
            String[] in = info[i].split(" ");
            Node node = new Node(in[0], in[1], in[2], in[3], Integer.parseInt(in[4]));
            subset(0, in, new StringBuilder(), node);
        }
        
        for (List<Node> list : map.values()) {
            Collections.sort(list);
        }
        
        int[] answer = new int[query.length];
        for(int i = 0; i < query.length; i++) {
            int cnt = 0;
            
            String[] q = query[i].split("and");
            String[] s = q[3].trim().split(" ");
            int score = Integer.parseInt(s[1]);
            StringBuilder sb = new StringBuilder();
            sb.append(q[0].trim()).append(" and ").append(q[1].trim()).append(" and ").append(q[2].trim()).append(" and ").append(s[0]);
            
            List<Node> list = map.getOrDefault(sb.toString(), new ArrayList<>());
  
            if(list.isEmpty()) {
                answer[i] = 0;
                continue;
            }
            
            int left = 0;
            int right = list.size() - 1;
            
            
            while(left <= right) {
                int mid = (left + right) / 2;
                
                if(list.get(mid).score < score) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            
            answer[i] = list.size() - left;
        }
        
        return answer;
    }
    
    private void subset(int cnt, String[] strs, StringBuilder q, Node node) {
        if (cnt == 4) {
            List<Node> list = map.getOrDefault(q.toString(), new ArrayList<Node>());
            list.add(node);
            map.put(q.toString(), list);
            return;
        }

        int originalLength = q.length();
        String and = cnt < 3 ? " and " : "";
        q.append(strs[cnt]).append(and);
        subset(cnt + 1, strs, q, node);
        q.setLength(originalLength);

        q.append("-").append(and);
        subset(cnt + 1, strs, q, node);
    }

    
    static class Node implements Comparable<Node> {
        String language;
        String job;
        String carrer;
        String food;
        int score;
        
        public Node (String language, String job, String carrer, String food, int score) {
            this.language = language;
            this.job = job;
            this.carrer = carrer;
            this.food = food;
            this.score = score;
        }
        
        @Override
        public int compareTo(Node a) {
            return this.score - a.score;
        }
    }
}
