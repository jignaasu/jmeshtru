import PrimeUtils.isPrime

object Problem51_PrimeDigit {

    @JvmStatic
    fun main(args: Array<String>) {
        // Generate all the primes in 6 digit range
        val primeList = (100_000..400_000)
                .filter { it.isPrime() }
                .filter { it.toString().toList().groupingBy { it }.eachCount().containsValue(3) }
                .toList()

        var numMatchedCount = 0
        for(i in  primeList) {
            for(j in 0..9) {
                val repeatedKey = i.toString().toList().groupingBy { it }.eachCount()
                        .entries.find { it.value == 3 }?.key
                        ?: continue

                if (repeatedKey.toInt() == j)
                    continue

                val checkPrime = i.toString().replace(repeatedKey.toString(), j.toString()).toInt()
                if (checkPrime.toString().length == 6 && checkPrime.isPrime())
                    numMatchedCount++

            }
            if (numMatchedCount == 8) {
                print(i)
                break;
            }

            numMatchedCount = 0
        }
    }
}
