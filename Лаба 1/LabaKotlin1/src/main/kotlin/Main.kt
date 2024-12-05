fun main() {
    var number: Int
    val numbers = mutableListOf<Int>()
    var div :Int = 2
    var count:Int = 0
    while (true) {
        print("Введите число: ")
        try {
            // Читаем ввод и проверяем на null или пустую строку
            val input = readLine()

            if (input.isNullOrBlank()) {
                throw NullPointerException("Ввод не может быть пустым.")
            }

            // Пробуем преобразовать строку в Int
            number = input.toInt()

            if (number < 0) {
                throw IllegalArgumentException("Число не может быть отрицательным")
            }
            if(number==0){
                throw IllegalArgumentException("Число не может быть нулем")
            }


            // Если все прошло хорошо, выходим из цикла
            break
        } catch (e: NullPointerException) {
            println(e.message)
        } catch (e: NumberFormatException) {
            println("Неправильный ввод. Введите корректное число.")
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }


    while (number % div == 0) {
        numbers.add(div)
        number/=div
        count++
    }
    // Проверяем делители начиная с 3, пропуская четные числа
    div = 3
    while (div * div <= number) {
        while (number % div == 0) {
            numbers.add(div)
            count++
            number/=div
        }
        div += 2 // Пропускаем четные числа
    }
    numbers.add(1)
    // Если остаток n > 2, это простое число
    if (number > 2) {
        numbers.add(number)
        count++
    }
    if(count==1) println("Число ${number} простое")
    // Вывод простых множителей
    println("Простые множители числа: $numbers")

}