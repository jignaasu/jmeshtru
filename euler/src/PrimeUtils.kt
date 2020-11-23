object PrimeUtils {

    fun Int.isEven() : Boolean = this % 2 == 0

    fun Int.isPrime(): Boolean {
        if (this <= 3 )
            return this > 1

        if ((this % 2 == 0) or (this % 3 == 0))
            return false

        var i = 5
        while (i * i <= this) {
            if ((this % i == 0) or (this % (i + 2) == 0))
                return false

            i += 6
        }

        return true
    }
}
