import java.util.*;

class Solution {
    
    static int idx;
    
    public String solution(String p) {
        // 빈 문자열이면 그대로 반환
        if (p.equals("")) {
            return p;
        }
        
        // u와 v로 분리
        String u = p.substring(0, idx(p) + 1);  // 균형잡힌 문자열 분리
        String v = p.substring(idx(p) + 1);    // 나머지 부분
        
        // u가 올바른 괄호 문자열이면 v에 대해 재귀 호출
        if (check(u)) {
            return u + solution(v);
        } else {
            // u가 올바르지 않으면 새로운 문자열 구성
            String answer = "(";
            answer += solution(v);
            answer += ")";
            
            String s = u.substring(1, u.length() - 1); // 괄호 양 끝 제거
            answer += reverse(s); // 괄호 반전 후 추가
            return answer;
        }
    }
    
    // 균형잡힌 괄호 문자열을 찾아서 분리할 인덱스를 반환
    public int idx(String str) {
        int open = 0, close = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') open++;
            else close++;
            
            if (open == close) return i; // 균형잡힌 시점 반환
        }
        return str.length() - 1;
    }
    
    // 문자열 반전 함수
    public String reverse(String s) {
        StringBuilder reverse = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                reverse.append(')');
            } else {
                reverse.append('(');
            }
        }
        return reverse.toString();
    }
    
    // 올바른 괄호 문자열인지 확인하는 함수
    public boolean check(String str) {
        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
