fun main() {
    val numbers = listOf(3, 7, 1, 9, 2, 6, 5)

    println("""Выберите стратегию обработки:
        |1. Сортировка
        |2. Фильтрация > 5
        |3. Агрегация (Сумма)
        |4. Агрегация (Среднее)
    """.trimMargin())

    val choice = readLine()?.toIntOrNull()

    val result = when (choice) {
        1 -> applyStrategy(numbers) { it.sorted() }
        2 -> applyStrategy(numbers) { it.filter { num -> num > 5 } }
        3 -> applyStrategy(numbers) { listOf(it.sum()) }              // обе агрегация
        4 -> applyStrategy(numbers) { listOf(it.average().toInt()) }
        else -> {
            println("Неверный выбор")
            return
        }
    }

    println("Результат обработки: $result")
}

fun applyStrategy(data: List<Int>, strategy: (List<Int>) -> List<Int>): List<Int> {
    return strategy(data)
}
