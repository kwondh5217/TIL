import java.util.*;

class Solution {
    static ArrayDeque<Integer>[] stack;
    static ArrayDeque<Integer> bucket;
    public int solution(int[][] board, int[] moves) {
        stack = new ArrayDeque[board[0].length];
        bucket = new ArrayDeque<>();
        for(int i = 0; i < board[0].length; i++) {
            stack[i] = new ArrayDeque<>();
            for(int j = board.length - 1; j >= 0; j--) {
                if(board[j][i] != 0) {
                    stack[i].push(board[j][i]);
                }
            }
        }
        
        int answer = 0;
        for(int i = 0; i < moves.length; i++) {
            int idx = moves[i] - 1;
            if(!stack[idx].isEmpty()) {
                int pop = stack[idx].pop();
                if(!bucket.isEmpty() && bucket.peek() == pop) {
                    bucket.pop();
                    answer += 2;
                } else {
                    bucket.push(pop);
                }
            }
        }
        
        return answer;
    }
}