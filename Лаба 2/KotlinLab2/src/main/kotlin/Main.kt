fun getInputArray(): IntArray {

    while (true) {
        try {
            print("Введите массив чисел через пробел: ")
            val input = readLine()

            if (input.isNullOrBlank()) {
                throw NullPointerException("Ввод не может быть пустым.")
            }

            // Преобразуем строку в массив целых чисел
            val array = input.split(" ").map { it.toInt() }.toIntArray()

            // Если массив пустой, то бросаем исключение
            if (array.isEmpty()) {
                throw IllegalArgumentException("Массив не может быть пустым.")
            }

            return array
        } catch (e: NullPointerException) {
            println(e.message)
        } catch (e: NumberFormatException) {
            println("Неправильный ввод. Убедитесь, что все элементы - это целые числа.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }
}

fun getTargetSum(): Int {
    while (true) {
        try {
            print("Введите целевую сумму: ")
            val input = readLine()

            if (input.isNullOrBlank()) {
                throw NullPointerException("Ввод не может быть пустым.")
            }

            val targetSum = input.toInt()
            return targetSum
        } catch (e: NullPointerException) {
            println(e.message)
        } catch (e: NumberFormatException) {
            println("Неправильный ввод. Введите корректное целое число.")
        }
    }
}

fun findSubarraysWithSum(arr: IntArray, targetSum: Int) {
    var found = false // Флаг, который отслеживает, были ли найдены подмассивы

    for (i in 0 until arr.size ) {
        var currentSum = 0
        for (j in i until arr.size) {
            currentSum += arr[j]
            if (currentSum == targetSum && j>i) {
                println("Подмассив с суммой $targetSum найден: индексы [$i, $j]")
                found=true
            }
        }
    }
    if (!found) {
        println("Нет подмассива с суммой $targetSum.")
    }
}

fun main() {

    while (true) {
        val arr = getInputArray()  // Получаем массив от пользователя
        val targetSum = getTargetSum() // Получаем целевую сумму от пользователя

        findSubarraysWithSum(arr, targetSum) // Находим подмассивы с заданной суммой

        while (true) {
            print("Хотите продолжить? (y/n): ")
            val input = readLine()
            when (input) {
                "y" -> break // Выходим из внутреннего цикла, чтобы продолжить выполнение
                "n" -> {
                    println("Программа завершена.")
                    return // Выходим из main, чтобы завершить программу
                }
                else -> println("Некорректный ввод. Введите 'y' для продолжения или 'n' для выхода.")
            }
        }
    }

}

