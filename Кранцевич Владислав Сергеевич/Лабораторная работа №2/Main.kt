import java.util.Scanner

fun main() {
    try {
        val scanner = Scanner(System.`in`)
        var dynamicArray = IntArray(0)

        while (true) {
            println("\nМеню:")
            println("1. Добавить элемент")
            println("2. Удалить элемент")
            println("3. Показать массив")
            println("4. Выйти")
            print("Выберите действие: ")

            when (scanner.nextInt()) {
                1 -> {
                    print("Введите элемент для добавления: ")
                    val element = scanner.nextInt()
                    val newArray = IntArray(dynamicArray.size + 1)
                    for (i in dynamicArray.indices) {
                        newArray[i] = dynamicArray[i]
                    }
                    newArray[dynamicArray.size] = element
                    dynamicArray = newArray
                    println("Элемент $element добавлен.")
                }


                2 -> {
                    if (dynamicArray.isEmpty()) {
                        println("Массив пуст, удалять нечего.")
                    } else {
                        print("Введите индекс элемента для удаления: ")
                        val index = scanner.nextInt()

                        if (index in 0 until dynamicArray.size) {
                            val newArray = IntArray(dynamicArray.size - 1)
                            for (i in 0 until index) {
                                newArray[i] = dynamicArray[i]
                            }
                            for (i in index until newArray.size) {
                                newArray[i] = dynamicArray[i + 1]
                            }
                            dynamicArray = newArray
                            println("Элемент с индексом $index удален.")
                        } else {
                            println("Некорректный индекс.")
                        }
                    }
                }

                3 -> {
                    if (dynamicArray.isEmpty()) {
                        println("Массив пуст.")
                    } else {
                        println("Текущий массив: ${dynamicArray.joinToString(", ")}")
                    }
                }

                4 -> {
                    println("Выход из программы.")
                    break
                }

                else -> {
                    println("Некорректный выбор. Попробуйте снова.")
                }
            }
        }
    } catch (e: Exception) {
        println("Ошибка ввода. Пожалуйста, введите корректные числовые значения.")
    }
}
