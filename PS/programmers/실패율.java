import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] arr = new int[N + 2];
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> {
            if(a.rate == b.rate) {
                return a.idx - b.idx;
            }
            return Double.compare(b.rate, a.rate);
        });
        for(int i = 0; i < stages.length; i++) {
            arr[stages[i]]++;
        }
        int playerCount = stages.length;
        for(int i = 1; i < arr.length - 1; i++) {
            if(arr[i] == 0 || playerCount == 0) {
                q.offer(new Node(i, 0));
            } else {
                q.offer(new Node(i, (double) arr[i]/playerCount));
                playerCount -= arr[i];
            }
            
        }
        
        int[] answer = new int[N];
        int idx = 0;
        while(!q.isEmpty()) {
            answer[idx++] = q.poll().idx;
        }
        
        return answer;
    }
    static class Node {
        int idx;
        double rate;
        public Node (int idx, double rate) {
            this.idx = idx;
            this.rate = rate;
        }
    }
}
