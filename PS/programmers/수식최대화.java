import java.util.*;

class Solution {
    static List<Character> list = new ArrayList<>();  // 연산자 종류 리스트
    static Set<Character> set = new HashSet<>();  // 연산자 중복 방지 세트
    static boolean[] check;
    static int[] nums;
    static List<String> calculate;  // 숫자와 연산자 리스트

    public long solution(String expression) {
        calculate = new ArrayList<>();
        StringBuilder num = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
    
            if (!Character.isDigit(currentChar)) {
                calculate.add(num.toString());  // 숫자 추가
                calculate.add(Character.toString(currentChar));  // 연산자 추가
                num = new StringBuilder();

                if (!set.contains(currentChar)) {
                    list.add(currentChar);
                    set.add(currentChar);
                }
            } else {
                num.append(currentChar);
            }
        }

        if (num.length() > 0) {
            calculate.add(num.toString());
        }

        check = new boolean[list.size()];
        nums = new int[list.size()];

        return perm(0);
    }

    // 순열을 생성하며 최대값을 찾는 함수
    private long perm(int idx) {
        if (idx == list.size()) {
            return simulation();
        }

        long maxResult = 0;

        for (int i = 0; i < list.size(); i++) {
            if (!check[i]) {
                check[i] = true;
                nums[idx] = i;
                maxResult = Math.max(maxResult, perm(idx + 1));
                check[i] = false;
            }
        }
        
        return maxResult;
    }

    // 우선순위 순열에 따라 수식을 평가
    private long simulation() {
        List<String> expr = new ArrayList<>(calculate);  // 원래 식을 복사
        for (int i = 0; i < nums.length; i++) {
            char op = list.get(nums[i]);
            for (int j = 1; j < expr.size() - 1; j++) {
                if (expr.get(j).equals(String.valueOf(op))) {
                    long a = Long.parseLong(expr.get(j - 1));
                    long b = Long.parseLong(expr.get(j + 1));
                    long result = cal(a, b, op);
                    expr.set(j - 1, String.valueOf(result));
                    expr.remove(j);  // 연산자 제거
                    expr.remove(j);  // 오른쪽 피연산자 제거
                    j--;
                }
            }
        }
        return Math.abs(Long.parseLong(expr.get(0)));
    }

    private long cal(long a, long b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0;
    }
}
