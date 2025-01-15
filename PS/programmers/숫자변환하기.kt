class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {
        val dp = IntArray(y + 1) { Int.MAX_VALUE }
        dp[x] = 0

        for (i in x + 1..y) {
            if (i - n >= x && dp[i - n] != Int.MAX_VALUE) {
                dp[i] = minOf(dp[i], dp[i - n] + 1)
            }
            if (i % 2 == 0 && i / 2 >= x && dp[i / 2] != Int.MAX_VALUE) {
                dp[i] = minOf(dp[i], dp[i / 2] + 1)
            }
            if (i % 3 == 0 && i / 3 >= x && dp[i / 3] != Int.MAX_VALUE) {
                dp[i] = minOf(dp[i], dp[i / 3] + 1)
            }
        }

        return if (dp[y] == Int.MAX_VALUE) -1 else dp[y]
    }
}