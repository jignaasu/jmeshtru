import java.math.BigInteger

object Problem57_SqrtConvergents {

    @JvmStatic
    fun main(args: Array<String>) {

        var previousNumerator: BigInteger = BigInteger.valueOf(3); var previousDenominator: BigInteger = BigInteger.TWO
        var answer = 0

        for (i in 2..1000) {
            val tempNumerator: BigInteger = previousNumerator + previousDenominator.shiftLeft(1)
            val tempDenominator: BigInteger = previousNumerator + previousDenominator

            if (tempNumerator.toString().length > tempDenominator.toString().length) {
                answer+=1
                println("Iteration $i $tempNumerator / $tempDenominator")

            }

            previousNumerator = tempNumerator; previousDenominator = tempDenominator
        }

        println(answer)
    }
}
