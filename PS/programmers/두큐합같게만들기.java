import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long q1Sum = 0L;
        long q2Sum = 0L;

        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            q1Sum += queue1[i];
            q2.add(queue2[i]);
            q2Sum += queue2[i];
        }

        long totalSum = q1Sum + q2Sum;

        // 두 큐의 합이 홀수면 절대 같아질 수 없으므로 -1 반환
        if (totalSum % 2 != 0) {
            return -1;
        }

        long targetSum = totalSum / 2;

        int maxOperations = queue1.length * 4;
        int operations = 0;

        while (operations < maxOperations) {
            if (q1Sum == targetSum) {
                return operations;
            }

            if (q1Sum > targetSum) {
                int value = q1.poll();
                q1Sum -= value;
                q2.add(value);
            } else {
                int value = q2.poll();
                q2Sum -= value;
                q1.add(value);
                q1Sum += value;
            }

            operations++;
        }

        return -1;
    }
}
