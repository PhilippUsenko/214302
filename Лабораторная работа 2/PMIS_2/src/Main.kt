import kotlin.random.Random

fun createMatrix(size: Int, minValue: Int, maxValue: Int): Array<IntArray> {
    val matrix = Array(size) { IntArray(size) }
    for (i in 0 until size) {
        for (j in 0 until size) {
            matrix[i][j] = Random.nextInt(minValue, maxValue + 1)
        }
    }
    return matrix
}

fun transposeMatrix(matrix: Array<IntArray>): Array<IntArray> {
    val size = matrix.size
    val transposedMatrix = Array(size) { IntArray(size) }
    for (i in 0 until size) {
        for (j in 0 until size) {
            transposedMatrix[j][i] = matrix[i][j]
        }
    }
    return transposedMatrix
}

fun printMatrix(matrix: Array<IntArray>) {
    for (i in matrix) {
        println(i.joinToString(" "))
    }
}

fun positiveInt(prompt: String): Int {
    var number: Int?
    do {
        println(prompt)
        val input = readLine()
        number = input?.toIntOrNull()
        if (number == null || number <= 0) {
            println("Ошибка: введите целое положительное число.")
        }
    } while (number == null || number <= 0)
    return number
}

fun checkInt(prompt: String): Int {
    var number: Int?
    do {
        println(prompt)
        val input = readLine()
        number = input?.toIntOrNull()
        if (number == null) {
            println("Ошибка: введите целое число.")
        }
    } while (number == null)
    return number
}

fun readRange(): Pair<Int, Int> {
    var minValue: Int
    var maxValue: Int
    do {
        minValue = checkInt("Введите минимальное значение диапазона (целое число):")
        maxValue = checkInt("Введите максимальное значение диапазона (целое число):")

        if (maxValue < minValue) {
            println("Ошибка: максимальное значение должно быть больше или равно минимальному.")
        }
    } while (maxValue < minValue)
    return Pair(minValue, maxValue)
}

fun main() {

    val size = positiveInt("Введите размер матрицы (целое положительное число):")
    val (minValue, maxValue) = readRange()

    val matrix = createMatrix(size, minValue, maxValue)
    println("Исходная матрица:")
    printMatrix(matrix)

    val transposedMatrix = transposeMatrix(matrix)
    println("Транспонированная матрица:")
    printMatrix(transposedMatrix)
}
