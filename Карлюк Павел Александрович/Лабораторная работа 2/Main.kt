fun main() {

    var length: Int

    print("Введите длину первого массива: ")
    length = generateLength()
    var firstArray: Array<Float>
    firstArray = generateArray(length)

    print("Введите длину второго массива: ")
    length = generateLength()
    var secondArray: Array<Float>
    secondArray = generateArray(length)

    var operations: String

    while(true){
        var max : Float
        var min : Float

        println("Для выхода и отображения - 0")
        println("Введите операцию (>, >=, <, <=, четность, нечетность): ")
        operations = readLine().toString().lowercase()

        when(operations){
            ">" -> {

                println("Введите число: ")
                while (true){
                    max = readLine()?.toFloatOrNull()!!
                    if(!max.equals(null) ){
                        firstArray = firstArray.filter{ it > max}.toTypedArray()
                        secondArray = secondArray.filter{ it > max}.toTypedArray()
                        break
                    }
                    else{
                        println("Некорретный ввод. Попробуйте снова")
                    }
                }
            }
            "<" -> {

                println("Введите число: ")
                while (true){
                    min = readLine()?.toFloatOrNull()!!
                    if(!min.equals(null) ){
                        firstArray = firstArray.filter{ it < min}.toTypedArray()
                        secondArray = secondArray.filter{ it < min}.toTypedArray()
                        break
                    }
                    else{
                        println("Некорретный ввод. Попробуйте снова")
                    }
                }

            }

            ">=" -> {

                println("Введите число: ")
                while (true){
                    max = readLine()?.toFloatOrNull()!!
                    if(!max.equals(null) ){
                        firstArray = firstArray.filter{ it >= max}.toTypedArray()
                        secondArray = secondArray.filter{ it >= max}.toTypedArray()
                        break
                    }
                    else{
                        println("Некорретный ввод. Попробуйте снова")
                    }
                }


            }
            "<=" -> {

                println("Введите число: ")
                while (true){
                    min = readLine()?.toFloatOrNull()!!
                    if(!min.equals(null) ){
                        firstArray = firstArray.filter{ it <= min}.toTypedArray()
                        secondArray = secondArray.filter{ it <= min}.toTypedArray()
                        break
                    }
                    else{
                        println("Некорретный ввод. Попробуйте снова")
                    }
                }
            }

            "четность" ->{

                firstArray = firstArray.filter{ it % 2 == 0f }.toTypedArray()
                secondArray = secondArray.filter{ it % 2 == 0f }.toTypedArray()

            }
            "нечетность" ->{

                firstArray = firstArray.filter{ it % 2 != 0f }.toTypedArray()
                secondArray = secondArray.filter{ it % 2 != 0f }.toTypedArray()

            }
            "0" ->{

                val intersection = firstArray.intersect(secondArray.toSet())
                val union = firstArray.union(secondArray.toSet())

                println("Пересечение: $intersection")
                println("Объединение: $union")
                break

            }
            else -> {
                println("Некорректный ввод. Попробуйте снова")
            }
        }
    }
}

fun generateLength(): Int{

    while(true) {
        val input = readLine()?.toIntOrNull()
        if(input != null  && input > 0) {
            return input.toInt()
        }
        else{
            println("Неккоретный ввод. Попробуйте снова")
        }
    }

}

fun generateArray(length: Int) : Array<Float> {

    println("Введите $length элементов массива: ")

    var counter = 0
    val array = FloatArray(length)
    while(counter < length) {

        val input = readLine()?.toFloatOrNull()

        if(input != null) {
            array[counter] = input.toFloat()
            counter++
        }
        else{
            println("Неккоретный ввод. Попробуйте снова")
        }
    }

    return array.toTypedArray()

}

