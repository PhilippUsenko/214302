fun main() {
    var numbers = mutableListOf<Int>()
    var length = (6..10).random()
    for (x in 1..length) {
        var i = (-20..20).random()
        numbers += i;
    }
    println("\nНачальный массив:")
    for (i in numbers) {
        print("$i\t")
    }
    numbers= numbers.map({ i->i+1 }).toMutableList()
    println("\nНачальный массив:")
    for (i in numbers) {
        print("$i\t")
    }

    var number: Int? = null
    do {
        println(
            "\n\tВыберите действие: \n1 - Найти максимальный элемент\n" +
                    "2 - Найти сумму элементов\n3 - Выбрать случайный элемент" +
                    "\n4 - Посчитать количество элементов\n5 - Найти среднее арифметическое элементов" +
                    "\n0 - ВЫЙТИ"
        )
        while (true) {
            print("Введите число: ")
            number = (readLine()?.trim(' '))?.toIntOrNull()
            if (number != null) {
                break
            } else println("Введенное значение не является числом")
        }
        val operation: (List<Int>) -> Int
        operation = when (number) {
            1 ->
                { nums ->
                    nums.max()
                }

            2 ->
                { nums -> nums.sum() }

            3 ->
                { nums -> nums.random() }

            4 ->
                { nums -> nums.count() }

            5 ->
                { nums -> nums.sum() / nums.size }

            else -> return
        }
        val maxNumber = operation(numbers)
        println("Результат операции: $maxNumber")
    } while (number != 0)
}