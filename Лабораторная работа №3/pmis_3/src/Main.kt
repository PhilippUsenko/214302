import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.Scanner

typealias Operation = (Double, Double) -> Double

class OperationNotFoundException(message: String): Exception(message)

class DynamicCalculator {
    private val operations = mutableMapOf<String, Operation>()

    fun addOperation(name: String, operation: Operation) {
        operations[name] = operation
    }

    @Throws(OperationNotFoundException::class)
    fun applyOperation(name: String, a: Double, b: Double): Double? {
        val operation = operations[name] ?: throw OperationNotFoundException("Операция '$name' не найдена.")
        return operation.invoke(a, b)
    }

    fun saveOperations(filename: String) {
        ObjectOutputStream(File(filename).outputStream()).use { it.writeObject(operations) }
    }

    fun loadOperations(filename: String) {
        ObjectInputStream(File(filename).inputStream()).use {
            @Suppress("UNCHECKED_CAST")
            operations.putAll(it.readObject() as Map<String, Operation>)
        }
    }

    fun listOperations() {
        operations.keys.forEach { println(it) }
    }
}

fun main() {
    val scanner = Scanner(System.`in`)
    val calculator = DynamicCalculator()

    while (true) {
        println("Выберите опцию: [1] Добавить операцию, [2] Применить операцию, [3] Сохранить операции, [4] Загрузить операции, [5] Выйти")
        when (scanner.nextInt()) {
            1 -> {
                println("Введите имя операции:")
                val name = scanner.next()
                println("Введите операцию (пример: + для сложения):")
                val symbol = scanner.next()
                when (symbol) {
                    "+" -> calculator.addOperation(name) { a, b -> a + b }
                    "-" -> calculator.addOperation(name) { a, b -> a - b }
                    "*" -> calculator.addOperation(name) { a, b -> a * b }
                    "/" -> calculator.addOperation(name) { a, b -> if (b != 0.0) a / b else Double.NaN }
                    else -> println("Неизвестная операция")
                }
                println("Операция добавлена")
            }
            2 -> {
                println("Доступные операции:")
                calculator.listOperations()
                println("Введите имя операции:")
                val name = scanner.next()
                println("Введите два числа:")
                val a = scanner.nextDouble()
                val b = scanner.nextDouble()
                try {
                    val result = calculator.applyOperation(name, a, b)
                    println("Результат: $result")
                } catch (e: OperationNotFoundException) {
                    println(e.message)
                }
            }
            3 -> {
                println("Введите имя файла для сохранения:")
                val filename = scanner.next()
                calculator.saveOperations(filename)
                println("Операции сохранены")
            }
            4 -> {
                println("Введите имя файла для загрузки:")
                val filename = scanner.next()
                calculator.loadOperations(filename)
                println("Операции загружены")
            }
            5 -> return
            else -> println("Неверная опция")
        }
    }
}
