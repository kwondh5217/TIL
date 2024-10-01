import java.util.*;

// 누적합
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] change = new int[board.length + 1][board[0].length + 1];
        for(int i = 0; i < skill.length; i++) {
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3];
            int c2 = skill[i][4];
            int degree = skill[i][0] == 1 ? -1 * skill[i][5] : skill[i][5];
            
            change[r1][c1] += degree;
            change[r1][c2 + 1] -= degree;
            change[r2 + 1][c1] -= degree;
            change[r2 + 1][c2 + 1] += degree;
        }
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 1; j < board[i].length; j++) {
                change[i][j] += change[i][j - 1];
            }
        }
        
        for(int i = 0; i < board[0].length; i++) {
            for(int j = 1; j < board.length; j++) {
                change[j][i] += change[j - 1][i];
            }
        }
        
        int answer = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                board[i][j] += change[i][j];
                if(board[i][j] > 0) {
                    answer++;
                }
            }
        }
        
        
        return answer;
    }
}
