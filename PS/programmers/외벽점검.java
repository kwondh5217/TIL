import java.util.*;

class Solution {
    static int[] weak;
    static int[] dist;
    static int result = Integer.MAX_VALUE;
    static int n;

    public int solution(int nInput, int[] weakInput, int[] distInput) {
        n = nInput;
        weak = weakInput;
        dist = distInput;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < weak.length; i++) {
            deque.offerLast(weak[i]);
        }

        // 모든 친구의 순열을 구해서 시도
        permute(0, new boolean[dist.length], new int[dist.length], deque);

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void permute(int cnt, boolean[] visit, int[] arr, ArrayDeque<Integer> deque) {
        if (cnt == dist.length) {
            for (int i = 0; i < weak.length; i++) {
                backTracking(0, 0, new ArrayDeque<>(deque), arr);
                deque.offerLast(deque.pollFirst());  // 시작점 회전
            }
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visit[i]) continue;
            arr[cnt] = dist[i];  // 순열 생성
            visit[i] = true;
            permute(cnt + 1, visit, arr, deque);
            visit[i] = false;
        }
    }

    private void backTracking(int cnt, int idx, ArrayDeque<Integer> deque, int[] arr) {
        if (deque.isEmpty()) {
            result = Math.min(cnt, result);
            return;
        }
        if (cnt >= result) {
            return;  // 가지치기
        }
        if (idx == arr.length) {
            return;  // 더 이상 사용할 친구가 없으면 종료
        }


        ArrayDeque<Integer> copy = new ArrayDeque<>(deque);
        int poll = copy.pollFirst();
        int size = arr[idx];

        while (size-- > 0) {
            poll = (poll + 1) % n;
            if (!copy.isEmpty() && copy.peekFirst() == poll) {
                copy.pollFirst();
            }
        }
        backTracking(cnt + 1, idx + 1, copy, arr);
    }
}
