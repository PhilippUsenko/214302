import kotlin.random.Random

fun main() {
    var n: Int
    while (true) {
        println("Введите размер магического квадрата :")
        n = readLine()?.toIntOrNull() ?: 0
        if (n < 1) {
            println("Размер должен быть положительным числом.")
        } else {
            printMagicSquare(generateMagicSquare(n))
            break
        }
    }
}

fun generateMagicSquare(n: Int): Array<IntArray> {
    println("Загрузка...")
    while (true) {
        val magicSquare = Array(n) { IntArray(n) { Random.nextInt(0, 10) } }
        if (isMagicSquare(magicSquare)) {
            return magicSquare
        }
    }
}

fun isMagicSquare(square: Array<IntArray>): Boolean {
    val n = square.size
    val sums = mutableListOf<Int>()

    // Считает сумму строк
    for (i in 0 until n) {
        sums.add(square[i].sum())
    }

    // Считает сумму столбцов
    for (i in 0 until n) {
        sums.add(square.sumOf { it[i] })
    }

    // Считает сумму диагоналей
    sums.add(square.indices.sumOf { square[it][it] })
    sums.add(square.indices.sumOf { square[it][n - it - 1] })

    // Проверяет, все ли суммы равны
    return sums.distinct().size == 1 // Количество уникальных значений == 1
}

fun printMagicSquare(magicSquare: Array<IntArray>) {
    for (row in magicSquare) {
        for (num in row) {
            print("$num\t")
        }
        println()
    }
}
