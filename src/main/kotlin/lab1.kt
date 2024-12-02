import kotlin.random.Random

fun main() {

    print("Введите желаемое количество чисел в комбинации: ")
    var size = readlnOrNull()?.toIntOrNull()
    while(size == null) {
            println("Ошибка ввода. Попробуйте ещё раз")
            size = readlnOrNull()?.toIntOrNull()
            continue
    }

    val generatedCombination = List(size) { Random.nextInt(0, 5) }
    while (true) {
        var positions = 0
        print("Введите вашу комбинацию из $size чисел через пробел: ")
        val userCombination = readlnOrNull()?.split(" ")?.filter { it.isNotEmpty() }?.map { it.toInt() }

        if (userCombination == null || userCombination.size != size) {
            println("Пожалуйста, введите $size чисел.")
            continue
        }

        val sameNumbers = mutableListOf<Int>()

        for(i in 0..< size){
            for(j in 0..< size){
                if(generatedCombination[i] == userCombination[j]){
                    sameNumbers.add(generatedCombination[i])
                    if(i == j){
                        positions++
                    }
                }
            }
        }

        val uniqueSameNumbers = sameNumbers.toSet()
        if(positions == size){
            break
        }
        println("Чисел на правильных местах: $positions")
        println("Правильных чисел не на своих местах: ${uniqueSameNumbers.size - positions}")
    }
    println("\nПоздравляем! Вы выиграли!!!")
    println("Исходная сгенерированная комбинация: $generatedCombination")
}