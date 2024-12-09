import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)



    try {


        for (a in 1..10) {
            if (a % 2 == 0){
                println("Число чётное - $a")
            }
            else {
                println ("Число нечётное - $a")
            }
        }

        print("Введите коэффициент a: ")
        val a = scanner.nextDouble()

        print("Введите коэффициент b: ")
        val b = scanner.nextDouble()

        if (a == 0.0) {
            if (b == 0.0) {
                println("Уравнение имеет бесконечное множество решений.")
            } else {
                println("Уравнение не имеет решений, так как a = 0 и b != 0.")
            }
        } else {
            val x = -b / a
            println("Корень уравнения: x = $x")
        }

    } catch (e: Exception) {
        println("Ошибка ввода. Пожалуйста, введите корректные числовые значения.")
    }
}
