import java.util.*;

class Solution {
    
    static class Node {
        int x, y;
        int idx;
        Node left;
        Node right;
        public Node(int x, int y, int idx) {
            this.x = x;
            this.y = y;
            this.idx = idx;
        }
        
        public void add(Node node) {
            if(node.x < this.x) {
                if(left == null) {
                    left = node;
                } else {
                    left.add(node);
                }
            } else if(node.x > this.x) {
                if(right == null) {
                    right = node;
                } else {
                    right.add(node);
                }
            }
        }
        
        public void preorder() {
            preorders.add(this.idx);
            if(left != null) {
                left.preorder();
            }
            if(right != null) {
                right.preorder();
            }
        }
        
        public void postorder() {
            if(left != null) {
                left.postorder();
            }
            if(right != null) {
                right.postorder();
            }
            postorders.add(this.idx);
        }
    }
    static List<Integer> preorders = new ArrayList<>();
    static List<Integer> postorders = new ArrayList<>();
    public int[][] solution(int[][] nodeinfo) {
        PriorityQueue<Node> q = new PriorityQueue<>((a, b) -> {
            if(b.y == a. y) {
                return a.x - b.x;
            }
            return b.y - a.y;
        });
        for(int i = 0; i < nodeinfo.length; i++) {
            Node node = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1);
            q.offer(node);
        }
        Node parent = q.poll();
        while(!q.isEmpty()) {
            Node node = q.poll();
            parent.add(node);
        }
        
        parent.preorder();
        parent.postorder();
        
        int[][] answer = new int[2][nodeinfo.length];
        for(int i = 0; i < preorders.size(); i++) {
            answer[0][i] = preorders.get(i);
        }
        for(int i = 0; i < postorders.size(); i++) {
            answer[1][i] = postorders.get(i);
        }
        
        return answer;
    }
}
