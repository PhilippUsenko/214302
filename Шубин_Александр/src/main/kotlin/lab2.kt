
fun main() {

    print("Введите желаемое количество элементов в массивах: ")
    var size = readlnOrNull()?.toIntOrNull()
    while(size == null) {
        println("Ошибка ввода. Попробуйте ещё раз")
        size = readlnOrNull()?.toIntOrNull()
        continue
    }

    while (true) {

        var cycleCount = 0
        print("Введите элементы 1-го массива из $size чисел через пробел: ")
        val array1 = readlnOrNull()?.split(" ")?.filter { it.isNotEmpty() }?.map { it.toInt() }

        if (array1 == null || array1.size != size) {
            println("Пожалуйста, введите $size чисел.")
            continue
        }

        print("Введите элементы 2-го массива из $size чисел через пробел: ")
        val array2 = readlnOrNull()?.split(" ")?.filter { it.isNotEmpty() }?.map { it.toInt() }

        if (array2 == null || array2.size != size) {
            println("Пожалуйста, введите $size чисел.")
            continue
        }
        val userCondition1: (Int) -> Boolean = userMenu()
        var complexCondition: (Int) -> Boolean = { true }
        while(cycleCount < 1) {
            println("Желаете выбрать дополнительное условие? (это можно сделать только один раз. " +
                    "Выбрать более двух условий для фильтрации нельзя)\n1. ДА\nНЕТ - любая другая клавиша")
            val input = readlnOrNull()?.toIntOrNull()
            complexCondition = if(input == 1){
                val userCondition2: (Int) -> Boolean = userMenu()
                ({ num -> userCondition1(num) && userCondition2(num) })
            }else {
                userCondition1
            }
            cycleCount++
        }

        val (intersection, union) = filterArrays(complexCondition, array1, array2)

        println("Пересечение: $intersection")
        println("Объединение: $union")
        break
    }
}

fun filterArrays(condition: (Int) -> Boolean, array1: List<Int>, array2: List<Int>):
        Pair<Set<Int>, Set<Int>> {
    val filteredArray1 = array1.filter(condition)
    val filteredArray2 = array2.filter(condition)

    val intersection = filteredArray1.intersect(filteredArray2)
    val union = filteredArray1.union(filteredArray2)

    return Pair(intersection, union)
}

fun userMenu(): (Int) -> Boolean {
    while(true) {
        println(
            "Выберите условие для фильтрации:" +
                    "\n1. Числа должны быть чётными\n2. Числа должны быть нечётными" +
                    "\n3. Числа должны делиться без остатка на выбранное число" +
                    "\n4. Числа должны быть больше указанного числа" +
                    "\n5. Числа должны быть меньше указанного числа"
        )

        var input = readlnOrNull()?.toIntOrNull()
        var userCondition: (Int) -> Boolean

        val condition = when (input) {
            1 -> {
                userCondition = {it % 2 == 0}
            }
            2 ->{
                userCondition = {it % 2 != 0}
            }
            3 -> {
                println("Введите желаемый делитель: ")
                val customDivider = readlnOrNull()?.toIntOrNull()
                userCondition = {it % customDivider!! == 0}
            }
            4 -> {
                println("Введите число для сравнения: ")
                val customComparedNumber = readlnOrNull()?.toIntOrNull()
                userCondition = {it > customComparedNumber!! }
            }
            5 -> {
                println("Введите число для сравнения: ")
                val customComparedNumber = readlnOrNull()?.toIntOrNull()
                userCondition = {it < customComparedNumber!! }
            }
            else -> {
                println("Ошибка ввода. Выберите существующий пункт меню")
                continue
            }
        }
        return userCondition
    }
}
