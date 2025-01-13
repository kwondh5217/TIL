class Solution {
    fun solution(numbers: IntArray): IntArray {
        val arrayDeque = ArrayDeque<Int>()
        val answer = IntArray(numbers.size)

        for (i in numbers.size - 1 downTo 0) {
            while (!arrayDeque.isEmpty() && arrayDeque.last() <= numbers[i]) {
                arrayDeque.removeLast()
            }

            answer[i] = if (arrayDeque.isEmpty()) -1 else arrayDeque.last()

            arrayDeque.addLast(numbers[i])
        }
        return answer
    }
}