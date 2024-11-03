class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= m * t; i++) {
            sb.append(convert(Integer.toString(i, n)));
        }
        
        StringBuilder answer = new StringBuilder();
        for(int i = p - 1; i <= sb.length() && answer.length() < t; i += m) {
            answer.append(sb.charAt(i));
        }
        
        return answer.toString();
    }
    
    private String convert(String str) {
        str = str.replaceAll("a", "A");
        str = str.replaceAll("b", "B");
        str = str.replaceAll("c", "C");
        str = str.replaceAll("d", "D");
        str = str.replaceAll("e", "E");
        str = str.replaceAll("f", "F");
        return str;
    }
}
