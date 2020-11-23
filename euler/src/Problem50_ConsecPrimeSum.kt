import PrimeUtils.isEven
import PrimeUtils.isPrime

object Problem50_ConsecPrimeSum {

    @JvmStatic
    fun main(args: Array<String>) {
        val primeList = (2..1_000_000).filter { it.isPrime() }.toList()
        val limit = 1_000_000
        val firstIndex = 0
        var longestStreak = 0
        var answer: Pair<Int, Int> = Pair(0, 0)
        for(i in firstIndex..50) {
            var lastIndex = i + 1
            val list = mutableListOf(primeList[i])
            var sum = primeList[i]
            while (sum < limit) {
                val interimSum = sum + primeList[lastIndex]
                sum = interimSum
                list.add(primeList[lastIndex])
                if (interimSum.isPrime() && sum < limit && longestStreak <= list.size) {
                    answer =  Pair(sum, list.size)
                    longestStreak = list.size
                }
                lastIndex++
            }
        }

        println(answer)
    }
}
