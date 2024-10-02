class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        char[][] chars = new char[n][n];
        // 초기화
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                chars[i][j] = ' ';
            }
        }
        // 해독
        for(int i = 0; i < n; i++) {
            String s = Integer.toString(arr1[i] | arr2[i], 2);
            for(int j = n - s.length(); j < n; j++) {
                chars[i][j] = s.charAt(j - (n - s.length())) == '1' ? '#' : ' ';
            }
        }
        
        String[] answer = new String[n];
        for(int i = 0; i < n; i++) {
            answer[i] = String.valueOf(chars[i]);
        }
        return answer;
    }
    
    
}
