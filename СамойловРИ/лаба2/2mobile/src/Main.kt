import java.util.Scanner

fun reverseAndInvert(arr: IntArray): IntArray {
    return arr.reversed().map { -it }.toIntArray()
}

fun main() {
    val scanner = Scanner(System.`in`)
    while (true) {
        println("Введите элементы массива через пробел:")
        val input = scanner.nextLine()
        val array = input.split(" ").mapNotNull { it.toIntOrNull() }.toIntArray()

        if (array.isEmpty()) {
            println("Ошибка: массив пуст или введены неверные данные.")
            continue
        }

        val result = reverseAndInvert(array)
        println("Реверсированный и инвертированный массив: ${result.joinToString(", ")}")

        println("\nХотите ввести новый массив? (да/нет)")
        val choice = scanner.nextLine().trim().lowercase()

        if (choice != "да") {
            println("Завершение программы.")
            break
        }
    }
}
