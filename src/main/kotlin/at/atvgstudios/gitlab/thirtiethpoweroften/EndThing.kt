package at.atvgstudios.gitlab.thirtiethpoweroften

class EndThing {
    private val wiring: Array<IntArray> = arrayOf(intArrayOf(0, 21), intArrayOf(1, 10), intArrayOf(2, 22), intArrayOf(3, 17), intArrayOf(4, 6), intArrayOf(5, 8), intArrayOf(6, 4), intArrayOf(7, 19), intArrayOf(8, 5), intArrayOf(9, 25), intArrayOf(10, 1), intArrayOf(11, 20), intArrayOf(12, 18), intArrayOf(13, 15), intArrayOf(14, 16), intArrayOf(15, 13), intArrayOf(16, 14), intArrayOf(17, 3), intArrayOf(18, 12), intArrayOf(19, 7), intArrayOf(20, 11), intArrayOf(21, 0), intArrayOf(22, 2), intArrayOf(23, 24), intArrayOf(24, 23), intArrayOf(25, 9))

    fun runThrough(input: Int, forward: Boolean): Int {
        val i = input % 26
        return if (forward) {
            wiring[i][1]
        } else {
            wiring[i][0]
        }
    }
}