import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        List<Long> bannedNumbers = new ArrayList<>();
        for (String ban : bans) {
            bannedNumbers.add(convertToLong(ban));
        }
        Collections.sort(bannedNumbers);

        long target = n;
        for (long banNum : bannedNumbers) {
            if (target >= banNum) {
                target++;
            } else {
                break;
            }
        }
        return convertToString(target);
    }

    private String convertToString(long num) {
        long number = num;
        StringBuilder sb = new StringBuilder();
        while (number-- > 0) {
            char c = (char) ('a' + (int)(number % 26));
            sb.append(c);
            number /= 26;
        }
        return sb.reverse().toString();
    }
    
    private long convertToLong(String input) {
        long result = 0L;
        for (int i = 0; i < input.length(); i++) {
            int value = input.charAt(i) - 'a' + 1;
            result = result * 26 + value;
        }
        return result;
    }
}
