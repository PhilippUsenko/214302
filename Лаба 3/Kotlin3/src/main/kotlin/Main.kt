fun main() {

    // Создаем замыкание для вычисления факториала с кэшированием
    fun createCachedFactorial(): (Long) -> Long {
        val cache = mutableMapOf<Long, Long>()

        return { n: Long ->
            // Внутренняя функция для вычисления факториала
            fun cachedFactorial(n: Long): Long {
                if (n == 0L) return 1
                if (cache.containsKey(n)) {
                    println("Возвращаем из кэша факториал($n)")
                    return cache[n]!!
                }

                println("Вычисляем факториал($n)")
                val result = n * cachedFactorial(n - 1)
                cache[n] = result
                return result
            }

            // Вызываем вычисление
            cachedFactorial(n)
        }
    }

    // Используем замыкание для кэшированного вычисления факториала
    val cachedFactorial = createCachedFactorial()

    while (true) {
        try {
            print("Введите число: ")
            val number = readLine()

            if (number.isNullOrBlank()) {
                throw NullPointerException("Ввод не может быть пустым.")
            }
            if (number.toInt() < 0) {
                throw NumberFormatException()
            }

            // Преобразуем введенное значение в Long и используем кэшированную функцию
            println("Факториал $number: ${cachedFactorial(number.toLong())}")

        } catch (e: NullPointerException) {
            println(e.message)
        } catch (e: NumberFormatException) {
            println("Неправильный ввод. Введите корректное целое число.")
        }
    }

}
