import PrimeUtils.isPrime
import kotlin.streams.toList

object Problem49_PrimePerm {

    @JvmStatic
    fun main(args: Array<String>) {
        val primeList = (1001..9999).filter { it.isPrime() }.toList()
        println(primeList.size)

        println("is Prime: " + isPerm(1234, 4721))

        for ( i in primeList)
            for (j in primeList) {
                val thirdPrime = j + ( j - i)
                if (j > i && primeList.contains(thirdPrime) && isPerm(i, j) && isPerm(i, thirdPrime))
                    println(listOf(i, j, thirdPrime))
            }
    }

    fun isPerm(num1: Int, num2: Int): Boolean {
        val num1List = num1.toString().chars().sorted().toList()
        val num2List = num2.toString().chars().sorted().toList()
        return num1List.equals(num2List)
    }
}
