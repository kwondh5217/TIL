class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200000000;
        int cnt = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            int zero = 0;
            boolean flag = true;

            for (int stone : stones) {
                if (stone < mid) {
                    zero++;
                    if (zero >= k) {
                        flag = false;
                        break;
                    }
                } else {
                    zero = 0;
                }
            }

            if (flag) {
                cnt = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return cnt;
    }
}
