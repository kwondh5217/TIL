class Solution {
    
    public String solution(String number, int k) {
        char[] chars = number.toCharArray();
        
        StringBuilder sb = new StringBuilder();
        
        int start = 0;
        k = chars.length - k;
        while(k --> 0) {
            int max = 0;
            int idx = start;
            for(int i = idx; i < chars.length - k; i++) {
                int b = chars[i] - '0';
                if(b > max) {
                    max = b;
                    start = i;
                }
            }
            sb.append(chars[start]);
            start++;
        }
        
        return sb.toString();
    }
}
