import java.util.*;

class Solution {
    public int solution(int n, int k) {
        // n을 k진법으로 변환
        String converted = Integer.toString(n, k);
        
        // 0을 기준으로 숫자를 나눔
        StringTokenizer st = new StringTokenizer(converted, "0");
        int answer = 0;
        
        // 각 숫자가 소수인지 확인
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (token.equals("")) {
                continue;
            }
            
            // 문자열로 분리된 토큰을 숫자로 변환 (long으로 처리)
            long convertedToken = Long.parseLong(token);
            
            // 1은 소수가 아니므로 건너뜀
            if (convertedToken <= 1) {
                continue;
            }
            
            // 소수 확인
            if (isPrime(convertedToken)) {
                answer++;
            }
        }
    
        return answer;
    }
    
    // 소수 판별 함수 (long을 사용하여 범위 문제 해결)
    private boolean isPrime(long num) {
        if (num <= 1) return false; // 1은 소수가 아님
        if (num == 2 || num == 3) return true; // 2와 3은 소수
        if (num % 2 == 0 || num % 3 == 0) return false; // 2와 3으로 나눠지면 소수 아님
        
        // 제곱근까지 확인
        for (long i = 5; i * i <= num; i += 1) {
            if(i % 2 == 0 || i % 3 == 0) {
                continue;
            }
            if (num % i == 0) {
                return false;
            }
        }
        
        return true; // 나눠지지 않으면 소수
    }
}
