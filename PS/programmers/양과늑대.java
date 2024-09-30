import java.util.*;

// BFS + 비트 마스킹
class Solution {
    static List<List<Integer>> list = new ArrayList<>();
    public int solution(int[] info, int[][] edges) {
        // 초기화
        for(int i = 0; i < info.length; i++) {
            list.add(new ArrayList<Integer>());
        }
        for(int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            list.get(from).add(to);
            list.get(to).add(from);
        }
        
        int max = 0;
        int current = (1 << info.length);
        boolean[][] visit = new boolean[info.length][current];
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 1, 0, 1));
        visit[0][1] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            int currentIdx = node.idx;
            int currentSheep = node.sheep;
            int currentWolf = node.wolf;
            int currentMask = node.mask;
            
            max = Math.max(max, currentSheep);
            
            for(int idx : list.get(currentIdx)) {
                int nextMask = currentMask | (1 << idx);
                
                if(visit[idx][nextMask]) continue;
                
                int nextNode = info[idx];
                
                int nextSheep = currentSheep;
                int nextWolf = currentWolf;
                
                // 지나온 경로라면 중복으로 카운트를 하면 안됨
                if((currentMask & (1 << idx)) == 0) {
                    nextSheep = nextNode == 0 ? currentSheep + 1 : currentSheep;
                    nextWolf = nextNode == 1 ? currentWolf + 1 : currentWolf;
                }
                
                // 늑대가 더 많아질 경우라면 탐색을 하지 않음
                if(nextSheep <= nextWolf) continue;
                
                q.offer(new Node(idx, nextSheep, nextWolf, nextMask));
                visit[idx][nextMask] = true;
            }
        }
        
        
        int answer = max;
        return answer;
    }
    
    static class Node {
        int idx;
        int sheep;
        int wolf;
        int mask;
        
        public Node (int idx, int sheep, int wolf, int mask) {
            this.sheep = sheep;
            this.idx = idx;
            this.wolf = wolf;
            this.mask = mask;
        }
    }
}
