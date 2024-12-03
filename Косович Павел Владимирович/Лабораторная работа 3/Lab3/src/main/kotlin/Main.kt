val double: (Int) -> Int = { it * 2 }
val increment: (Int) -> Int = { it + 1 }

val upperCase: (String) -> String = { it.uppercase() }
val addExclamation: (String) -> String = { "$it!" }

val reverseList: (List<Int>) -> List<Int> = { it.reversed() }
val addElementToList: (List<Int>) -> List<Int> = { it+2 }

fun <T> compose(vararg funcs: (T) -> T): (T) -> T {
    return { input: T ->
        funcs.fold(input) { acc, func -> func(acc) }
    }
}

fun selectFunctionForType(type: String): (Any) -> Any {
    return when (type) {
        "Int" -> {
            println("Выберите функции для чисел: (1) Удвоить, (2) Инкрементировать")
            val funcs = mutableListOf<(Int) -> Int>()
            while (true) {
                when (readln()) {
                    "1" -> funcs.add(double)
                    "2" -> funcs.add(increment)
                    "done" -> break
                    else -> println("Введите 1 или 2 для выбора функции, или 'done' для завершения.")
                }
            }
            compose(*funcs.toTypedArray()) as (Any) -> Any
        }

        "String" -> {
            println("Выберите функции для строк: (1) Привести к верхнему регистру, (2) Добавить восклицательный знак")
            val funcs = mutableListOf<(String) -> String>()
            while (true) {
                when (readln()) {
                    "1" -> funcs.add(upperCase)
                    "2" -> funcs.add(addExclamation)
                    "done" -> break
                    else -> println("Введите 1 или 2 для выбора функции, или 'done' для завершения.")
                }
            }
            compose(*funcs.toTypedArray()) as (Any) -> Any
        }

        "List" -> {
            println("Выберите функции для списков: (1) Перевернуть список, (2) Добавить элемент 2")
            val funcs = mutableListOf<(List<Int>) -> List<Int>>()
            while (true) {
                when (readln()) {
                    "1" -> funcs.add(reverseList)
                    "2" -> funcs.add(addElementToList)
                    "done" -> break
                    else -> println("Введите 1 или 2 для выбора функции, или 'done' для завершения.")
                }
            }
            compose(*funcs.toTypedArray()) as (Any) -> Any
        }

        else -> {
            throw IllegalArgumentException("Неизвестный тип данных.")
        }
    }
}

fun main() {
    val dataType = selectDataType()
    val function = selectFunctionForType(dataType)

    when (dataType) {
        "Int" -> {
            val input = getIntInput("Введите целое число:")
            val result = function(input) as Int
            println("Результат: $result")
        }

        "String" -> {
            val input = getStringInput("Введите строку:")
            val result = function(input) as String
            println("Результат: $result")
        }

        "List" -> {
            val input = getListInput("Введите элементы списка через пробел:")
            val result = function(input) as List<Int>
            println("Результат: $result")
        }
    }
}

fun selectDataType(): String {
    while (true) {
        try {
            println("Выберите тип данных: (1) Int, (2) String, (3) List<Int>")
            return when (readln().toInt()) {
                1 -> "Int"
                2 -> "String"
                3 -> "List"
                else -> throw IllegalArgumentException("Некорректный выбор.")
            }
        } catch (e: Exception) {
            println("Ошибка ввода: ${e.message}. Попробуйте снова")
        }
    }
}

fun getIntInput(prompt: String): Int {
    while (true) {
        try {
            println(prompt)
            return readln().toInt()
        } catch (e: NumberFormatException) {
            println("Ошибка: введите корректное целое число")
        }
    }
}

fun getStringInput(prompt: String): String {
    println(prompt)
    return readln()
}

fun getListInput(prompt: String): List<Int> {
    while (true) {
        try {
            println(prompt)
            return readln().split(" ").map { it.toInt() }
        } catch (e: NumberFormatException) {
            println("Ошибка: убедитесь, что вы вводите целые числа, разделённые пробелами")
        }
    }
}
