import kotlin.math.pow

fun userInputBase(): Int {
    println("Введите базу системы счисления от 2 до 36 (1-выход):")
    val base = readLine()?.toIntOrNull()
    return if (base != null && base in 2..36) {
        base
    } else base ?: 0
}

fun userInputNumber(): Int {
    println("Введите число")

    return readLine()?.toIntOrNull() ?: 1
}

fun convert(base:Int, value: String): String{

    var decimal = 0
    val length = value.length

    for (i in 0 until length) {

        val bit = value[length - 1 - i].toString().toInt()

        decimal += bit * base.toDouble().pow(i.toDouble()).toInt()
    }

    return decimal.toString()

}

fun main(args: Array<String>) {

    while(true){

        val base = userInputBase()
        when (base) {
            1 -> {
                return
            }
            0 -> {
                println("Неверный ввод")
            }
            else -> {
                val number = userInputNumber()

                val result = convert(base, number.toString())

                println("Результат: $result")

            }
        }
    }

}