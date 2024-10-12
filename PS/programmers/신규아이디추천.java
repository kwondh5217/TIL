import java.util.*;
class Solution {
    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder(new_id);
        Set<Character> allowSet = new HashSet<>();
        allowSet.add('-');
        allowSet.add('_');
        allowSet.add('.');
        for(int i = 0; i < 10; i++) {
            allowSet.add((char) (i + '0'));
        }
        
        int size = sb.length();
        for(int i = 0; i < size; i++) {
            if(Character.isLetter(sb.charAt(i))) {
                sb.setCharAt(i, convert(sb.charAt(i)));
                continue;
            }
            if(!allowSet.contains(sb.charAt(i))) {
                sb.deleteCharAt(i);
                i--;
                size--;
            }
        }
        sb = new StringBuilder(sb.toString().replaceAll("\\.+", "."));
        if(sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        if(sb.length() > 1 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        if(sb.length() == 0) {
            sb.append("a");
        }
        if(sb.length() >= 16) {
            sb = new StringBuilder(sb.substring(0, 15));
            if(sb.charAt(sb.length() - 1) == '.'){
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        
        if(sb.length() <= 2) {
            char c = sb.charAt(sb.length() - 1);
            int cnt = 3 - sb.length();
            for(int i = 0; i < cnt; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    private char convert(char ch) {
        return Character.toLowerCase(ch);
    }
    
    
    
    
}
