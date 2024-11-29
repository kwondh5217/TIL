import java.util.*;
class Solution {
    boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for(int i = 0; i < s.length(); i++) {
            if(stack.isEmpty()) {
                stack.push(s.charAt(i));
            }else if(stack.peek() == '('){
                if(s.charAt(i) == ')'){
                    stack.pop();
                }else {
                    stack.push(s.charAt(i));
                }
            }else {
                stack.push(s.charAt(i));
            }
        }

        return stack.isEmpty() ? true : false;
    }
}