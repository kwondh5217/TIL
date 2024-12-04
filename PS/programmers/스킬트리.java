import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skillTree : skill_trees) {
            Queue<Character> queue = new LinkedList<>();
            
            for (char s : skill.toCharArray()) {
                queue.add(s);
            }
            
            boolean isValid = true;
            for (char c : skillTree.toCharArray()) {
                if (queue.contains(c)) {
                    if (c == queue.peek()) {
                        queue.poll();
                    } else {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) answer++;
        }
        return answer;
    }
}