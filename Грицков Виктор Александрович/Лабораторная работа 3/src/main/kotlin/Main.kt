fun main() {
    var isAlive = true

    println("Данная программа предназначена для расчета функций высшего порядка")

    while (isAlive) {
        println("1. Сложить два числа \n" +
                "2. Вычесть два числа \n" +
                "3. Умножить два числа \n" +
                "4. Разделить два числа \n" +
                "5. Выход")

        print("Выберите действие: ")

        val choiceInput = readLine()

        if (choiceInput != null && choiceInput.isNotEmpty()) {
            try {
                val choice = choiceInput.toInt()
                if (choice == 5) {
                    isAlive = false
                    println("Выход из программы.")
                    continue
                }
                inputData(choice)
            } catch (e: NumberFormatException) {
                println("Ошибка: Ввод должен быть числом. Пожалуйста, попробуйте снова.")
            }
        } else {
            println("Ошибка: Ввод не может быть пустым. Пожалуйста, попробуйте снова.")
        }
    }
}

fun inputData(choice: Int) {
    var a: Int
    var b: Int

    try {
        println("Введите первое число:")
        a = readLine()!!.toInt()

        println("Введите второе число:")
        b = readLine()!!.toInt()

        val action = selectAction(choice)
        val result = action(a, b)
        println("Результат: $result")
    } catch (e: NumberFormatException) {
        println("Ошибка: Ввод должен быть числом. Пожалуйста, попробуйте снова.")
    } catch (e: ArithmeticException) {
        println("Ошибка: ${e.message}")
    }
}

fun selectAction(key: Int): (Int, Int) -> Int {
    return when (key) {
        1 -> ::sum
        2 -> ::subtract
        3 -> ::multiply
        4 -> ::divide
        else -> throw IllegalArgumentException("Неверный выбор действия.")
    }
}

fun divide(a: Int, b: Int): Int {
    if (b == 0) throw ArithmeticException("Деление на ноль невозможно.")
    return a / b
}

fun sum(a: Int, b: Int): Int{
    return a + b
}
fun subtract(a: Int, b: Int): Int{
    return a - b
}
fun multiply(a: Int, b: Int): Int{
    return a * b
}