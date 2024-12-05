import kotlin.math.max

typealias Operation = (Int, Int, Int) -> Int

fun generateOperation(type: String): Operation {
    return when (type) {
        "max" -> { a, b, c -> max(a, max(b, c)) }
        "min" -> { a, b, c -> minOf(a, b, c) }
        "sum" -> { a, b, c -> a + b + c }
        else -> throw IllegalArgumentException("Неизвестная операция: $type")
    }
}

fun performOperation(operation: Operation, a: Int, b: Int, c: Int) {
    println("Результат: ${operation(a, b, c)}")
}

fun main() {
    while (true) {
        println("Введите тип операции (max, min, sum) или 'exit' для выхода: ")
        val type = readLine()?.trim()

        if (type == "exit") {
            println("Выход из программы.")
            break
        }

        try {
            val operation = generateOperation(type ?: "max")

            println("Введите три числа:")
            val a = readLine()?.toIntOrNull() ?: 0
            val b = readLine()?.toIntOrNull() ?: 0
            val c = readLine()?.toIntOrNull() ?: 0

            performOperation(operation, a, b, c)
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }

        println("Хотите повторить? (да/нет)")
        val repeat = readLine()?.trim()?.lowercase()
        if (repeat != "да") {
            println("Программа завершена.")
            break
        }
    }
}
