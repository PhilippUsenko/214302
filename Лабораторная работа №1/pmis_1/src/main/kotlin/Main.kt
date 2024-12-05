fun main() {
    println("Введите выражение с переменными : ")
    val statement: String = readLine() ?: return
    val statementArray: CharArray = statement.toCharArray()
    val variables = mutableMapOf<Char, Double>()
    val numbers = mutableListOf<Double>()
    val operations = mutableListOf<Char>()
    var currentNumber = StringBuilder()
    var exit = true
    for (symbol in statementArray) {
        when {
            symbol.isLetter() -> {
                if (!variables.containsKey(symbol)) {
                    println("Введите значение $symbol : ")
                    val number = readLine()?.toDouble() ?: return
                    variables[symbol] = number
                }
                currentNumber.append(variables[symbol])
            }
            symbol.isDigit() -> {
                currentNumber.append(symbol)
            }
            symbol in listOf('+', '-', '*', '/', '%') -> {
                numbers.add(currentNumber.toString().toDouble())
                currentNumber = StringBuilder()
                operations.add(symbol)
            }
            symbol == ' ' -> continue
            else -> {
                println("Неверный ввод!")
                exit = false
                break
            }
        }
    }
    if (currentNumber.isNotEmpty()) {
        numbers.add(currentNumber.toString().toDouble())
    }
    if (exit) {
        val result = calculateResult(numbers, operations)
        println("Результат: $result")
    }
}

fun calculateResult(numbers: MutableList<Double>, operations: MutableList<Char>): Double {
    val highPriorityOps = listOf('*', '/', '%')
    var i = 0

    while (i < operations.size) {
        if (operations[i] in highPriorityOps) {
            val result = performOperation(numbers[i], numbers[i + 1], operations[i])
            numbers[i] = result
            numbers.removeAt(i + 1)
            operations.removeAt(i)
        } else {
            i++
        }
    }
    var result = numbers[0]
    for (j in operations.indices) {
        result = performOperation(result, numbers[j + 1], operations[j])
    }
    return result
}

fun performOperation(a: Double, b: Double, operation: Char): Double {
    return when (operation) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> a / b
        '%' -> a % b
        else -> throw IllegalArgumentException("Неизвестная операция: $operation")
    }
}
