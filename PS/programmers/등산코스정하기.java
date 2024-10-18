import java.util.*;

class Solution {
    static final int MAX = 10000001;
    static int mountain = Integer.MAX_VALUE;
    static int intensity = Integer.MAX_VALUE;
    static int[] dist;
    static List<List<int[]>> list;
    static Set<Integer> summitSet;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        // 초기화
        list = new ArrayList<>();
        summitSet = new HashSet<>();
        dist = new int[n + 1];
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });
        // 출입구
        for(int num : gates) {
            q.offer(new int[]{num, 0});
        }
        // 산봉우리 셋
        for(int num : summits) {
            summitSet.add(num);
        }
        Arrays.fill(dist, MAX);
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
    
        // 경로 추가
        for(int i = 0; i < paths.length; i++) {
            int x = paths[i][0];
            int y = paths[i][1];
            int w = paths[i][2];
            list.get(x).add(new int[]{y, w});
            list.get(y).add(new int[]{x, w});
        }
        
        while(!q.isEmpty()) {
            int[] poll = q.poll();
            
            int x = poll[0];
            int w = poll[1];

            if(summitSet.contains(x)) continue;
            if(dist[x] < w ) continue; 
            
            
            for(int[] arr : list.get(x)) {
                int next = arr[0];
                int weight = arr[1];
                int max = Math.max(w, weight);
                
                if(dist[next] > max) {
                    dist[next] = max;
                    q.offer(new int[]{next, Math.max(w, max)});
                }
            }
        }
        
        for(int num : summitSet) {
            if(dist[num] < intensity) {
                intensity = dist[num];
                mountain = num;
            } else if(dist[num] == intensity) {
                mountain = Math.min(mountain, num);
            }
        }
        int[] answer = {mountain, intensity};
        return answer;
    }
}