import java.util.*;

class Solution {
    static boolean[] check = new boolean[8];
    static int answer;
    static String[] datas;
    
    public int solution(int n, String[] data) {
        answer = 0;
        datas = data;
        perm(0, new int[8]);
        return answer;
    }
    
    private void perm(int idx, int[] positions) {
        if (idx == check.length) {
            if (isValid(positions)) {
                answer++;
            }
            return;
        }
        
        for (int i = 0; i < check.length; i++) {
            if (!check[i]) {
                check[i] = true;
                positions[idx] = i;
                perm(idx + 1, positions);
                check[i] = false;
            }
        }
    }
    
    private boolean isValid(int[] positions) {
        for (String str : datas) {
            char a = str.charAt(0);
            char b = str.charAt(2);
            char op = str.charAt(3);
            int t = str.charAt(4) - '0';
            
            int aIdx = positions[getIdx(a)];
            int bIdx = positions[getIdx(b)];
            int distance = Math.abs(aIdx - bIdx) - 1;
            
            if (op == '=' && distance != t) return false;
            if (op == '>' && distance <= t) return false;
            if (op == '<' && distance >= t) return false;
        }
        return true;
    }
    
    private int getIdx(char ch) {
        return switch (ch) {
            case 'A' -> 0;
            case 'C' -> 1;
            case 'F' -> 2;
            case 'J' -> 3;
            case 'M' -> 4;
            case 'N' -> 5;
            case 'R' -> 6;
            case 'T' -> 7;
            default -> -1;
        };
    }
}
