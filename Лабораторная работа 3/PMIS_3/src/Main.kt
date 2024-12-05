import kotlin.random.Random

fun main() {
    while (true) {
        println("Выберите тип функции, которую нужно сгенерировать:")
        println("1. Монотонно возрастающая")
        println("2. Монотонно убывающая")
        println("3. Линейная")
        println("4. Нелинейная")
        println("5. Четная")
        println("6. Нечетная")
        println("0. Выход")

        when (readLine()!!.toInt()) {
            1 -> {
                val (func, formula) = generateRandomIncreasingFunction()
                val optimizedFormula = optimizeFormula(formula)
                println("Сгенерированная функция: $formula")
                println("Оптимизированная функция: $optimizedFormula")
                if (checkIncreasingFunction(func)) {
                    println("Функция является монотонно возрастающей.")
                } else {
                    println("Функция не является монотонно возрастающей.")
                }
                printFunction(func)
            }
            2 -> {
                val (func, formula) = generateRandomDecreasingFunction()
                val optimizedFormula = optimizeFormula(formula)
                println("Сгенерированная функция: $formula")
                println("Оптимизированная функция: $optimizedFormula")
                if (checkDecreasingFunction(func)) {
                    println("Функция является монотонно убывающей.")
                } else {
                    println("Функция не является монотонно убывающей.")
                }
                printFunction(func)
            }
            3 -> {
                val (func, formula) = generateRandomLinearFunction()
                val optimizedFormula = optimizeFormula(formula)
                println("Сгенерированная функция: $formula")
                println("Оптимизированная функция: $optimizedFormula")
                if (checkLinearFunction(func)) {
                    println("Функция является линейной.")
                } else {
                    println("Функция не является линейной.")
                }
                printFunction(func)
            }
            4 -> {
                val (func, formula) = generateRandomNonLinearFunction()
                val optimizedFormula = optimizeFormula(formula)
                println("Сгенерированная функция: $formula")
                println("Оптимизированная функция: $optimizedFormula")
                if (checkNonLinearFunction(func)) {
                    println("Функция является нелинейной.")
                } else {
                    println("Функция не является нелинейной.")
                }
                printFunction(func)
            }
            5 -> {
                val (func, formula) = generateRandomEvenFunction()
                val optimizedFormula = optimizeFormula(formula)
                println("Сгенерированная функция: $formula")
                println("Оптимизированная функция: $optimizedFormula")
                if (checkEvenFunction(func)) {
                    println("Функция является четной.")
                } else {
                    println("Функция не является четной.")
                }
                printFunction(func)
                printFunctionReverse(func)
            }
            6 -> {
                val (func, formula) = generateRandomOddFunction()
                val optimizedFormula = optimizeFormula(formula)
                println("Оптимизированная функция: $optimizedFormula")
                println("Сгенерированная функция: $formula")
                if (checkOddFunction(func)) {
                    println("Функция является нечетной.")
                } else {
                    println("Функция не является нечетной.")
                }
                printFunction(func)
                printFunctionReverse(func)
            }
            0 -> break
            else -> println("Неверный выбор, попробуйте снова.")
        }
    }
}

fun printFunction(f: (Int) -> Int) {
    println("Результаты функции для значений от 1 до 10:")
    for (i in 1..10) {
        println("f($i) = ${f(i)}")
    }
}

fun printFunctionReverse(f: (Int) -> Int) {
    println("Результаты функции для значений от -1 до -10:")
    for (i in -1 downTo -10) {
        println("-f($i) = ${f(i)*-1}")
    }
}

fun generateRandomIncreasingFunction(): Pair<(Int) -> Int, String> {
    val a = Random.nextInt(1, 5)
    val b = Random.nextInt(1, 5)
    val c = Random.nextInt(1, 5)
    val formula = "f(x) = $a * x^2 + $b * x + $c"
    return { x: Int -> a * x * x + b * x + c } to formula
}

fun checkIncreasingFunction(f: (Int) -> Int): Boolean {
    for (i in 1..100) {
        if (f(i) <= f(i - 1)) {
            return false
        }
    }
    return true
}

fun generateRandomDecreasingFunction(): Pair<(Int) -> Int, String> {
    val a = Random.nextInt(-5, -1)
    val b = Random.nextInt(-5, -1)
    val c = Random.nextInt(1, 5)
    val formula = "f(x) = $a * x^2 + $b * x + $c"
    return { x: Int -> a * x * x + b * x + c } to formula
}

fun checkDecreasingFunction(f: (Int) -> Int): Boolean {
    for (i in 1..100) {
        if (f(i) >= f(i - 1)) {
            return false
        }
    }
    return true
}

fun generateRandomLinearFunction(): Pair<(Int) -> Int, String> {
    val a = Random.nextInt(1, 10)
    val b = Random.nextInt(-10, 10)
    val formula = "f(x) = $a * x + $b"
    return { x: Int -> a * x + b } to formula
}

fun checkLinearFunction(f: (Int) -> Int): Boolean {
    val delta = f(2) - f(1)
    for (i in 3..100) {
        if (f(i) - f(i - 1) != delta) {
            return false
        }
    }
    return true
}

fun generateRandomNonLinearFunction(): Pair<(Int) -> Int, String> {
    val a = Random.nextInt(1, 5)
    val b = Random.nextInt(-5, 5)
    val c = Random.nextInt(1, 10)
    val formula = "f(x) = $a * x^2 + $b * x + $c"
    return { x: Int -> a * x * x + b * x + c } to formula
}

fun checkNonLinearFunction(f: (Int) -> Int): Boolean {
    val delta = f(2) - f(1)
    for (i in 3..100) {
        if (f(i) - f(i - 1) == delta) {
            return false
        }
    }
    return true
}

fun generateRandomEvenFunction(): Pair<(Int) -> Int, String> {
    val a = Random.nextInt(1, 5)
    val c = Random.nextInt(0, 10)
    val formula = "f(x) = $a * x^2 + $c"
    return { x: Int -> a * x * x + c } to formula
}

fun checkEvenFunction(f: (Int) -> Int): Boolean {
    for (i in 1..100) {
        if (f(i) != f(-i)) {
            return false
        }
    }
    return true
}

fun generateRandomOddFunction(): Pair<(Int) -> Int, String> {
    val a = Random.nextInt(1, 5)
    val formula = "f(x) = $a * x^3"
    return { x: Int -> a * x * x * x } to formula
}

fun checkOddFunction(f: (Int) -> Int): Boolean {
    for (i in 1..100) {
        if (f(i) != -f(-i)) {
            return false
        }
    }
    return true
}

fun optimizeFormula(formula: String): String {
    val quadraticRegex = Regex("(-?\\d+) \\* x\\^2 \\+ (-?\\d+) \\* x \\+ (-?\\d+)")
    val quadraticMatch = quadraticRegex.find(formula)
    if (quadraticMatch != null) {
        val (aStr, bStr, cStr) = quadraticMatch.destructured
        val a = aStr.toInt()
        val b = bStr.toInt()
        val c = cStr.toInt()

        return if (a != 0 && b != 0) {
            "f(x) = x * (${a} * x + $b) + $c"
        } else {
            formula
        }}

    val linearRegex = Regex("(-?\\d+) \\* x \\+ (-?\\d+)")
    val linearMatch = linearRegex.find(formula)
    if (linearMatch != null){
        val (aStr, cStr) = linearMatch.destructured
        val a = aStr.toInt()
        val c = cStr.toInt()

        val gcdValue = gcd(a, c)
        return if (gcdValue > 1) {
            "f(x) = $gcdValue * (${a / gcdValue} * x + ${c / gcdValue})"
        } else {
            formula
        }
    }

    return formula
}

fun gcd(a: Int, b: Int): Int {
    if (b == 0) return kotlin.math.abs(a)
    return gcd(b, a % b)
}