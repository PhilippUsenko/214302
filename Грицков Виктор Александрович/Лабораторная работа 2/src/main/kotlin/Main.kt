import java.math.BigInteger
import java.lang.NumberFormatException

fun main() {
    print("Введите число: ")
    var isAlive = true
    var n = 0

    while (isAlive) {
        val input = readLine()

        if (input.isNullOrEmpty()) {
            println("\nОшибка: Ввод не может быть пустым. Пожалуйста, введите положительное число.")
            continue
        }
        try {
            n = input.toInt()
            if (n < 0) {
                println("\nОшибка: Введите положительное число.")
                continue
            }
            isAlive = false
        } catch (e: NumberFormatException) {
            println("\nОшибка: Введите целое положительное число.")
        }
    }

    val fibNumbers = getFibonacci(n)
    println("\nЧисла Фибоначчи до $n -> $fibNumbers")

    val primeSet = fibNumbers.filter { isPrime(it) }

    println("Простые числа Фибоначчи: ${primeSet.joinToString(", ")}")
}


fun getFibonacci(n: Int): List<BigInteger> {
    val fibList = mutableListOf<BigInteger>()
    var a = BigInteger.ZERO
    var b = BigInteger.ONE

    while (a <= BigInteger.valueOf(n.toLong())) {
        fibList.add(a)
        val c = a + b
        a = b
        b = c
    }
    return fibList
}

fun isPrime(num: BigInteger): Boolean {
    if (num < BigInteger.TWO) return false
    if (num == BigInteger.TWO) return true
    if (num.mod(BigInteger.TWO) == BigInteger.ZERO) return false

    var i = BigInteger.valueOf(3)
    val sqrtNum = num.sqrt()
    while (i <= sqrtNum) {
        if (num.mod(i) == BigInteger.ZERO) return false
        i += BigInteger.TWO
    }
    return true
}

