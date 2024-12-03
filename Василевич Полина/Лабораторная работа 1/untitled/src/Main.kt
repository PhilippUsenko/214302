fun isSimple(num: Int): Boolean {
    var div = 2
    while (div < (num / 2) + 1) {
        if (num % div == 0) return false
        else div += 1
    }
    return true
}

fun main() {
    var number: Int? = null
    while (true) {
        print("Введите число: ")
        number = (readLine()?.trim(' '))?.toIntOrNull()
        if (number != null) {
            break
        } else println("Введенное значение не является числом")
    }
    if (isSimple(number!!)) {
        println("Введенное число является простым")
    } else {
        var dividers = emptyArray<Int>()
        var div = 2
        while (div <= number!!) {
            if (isSimple(div)) {
                while (true) {
                    if (number!! % div == 0) {
                        dividers += div
                        number = number / div
                    } else break
                }
            }
            div++
        }
        for (div in dividers) {
            print("$div\t")
        }
    }

//    var arr = arrayOf<Array<Int>>()
//    for (i in 0..4){
//        var innerArray = arrayOf<Int>()
//        for(j in 0..4) {
//
//            innerArray += i+j
//        }
//        arr+=innerArray
//    }
//    for(value in arr){
//        for(n in value){
//            print("$n\t")
//        }
//        println()
//    }
//    var min = arr[0][0]
//    var max = arr[0][0]
//    for(value in arr){
//        for(n in value){
//            if(n<min) min = n
//            if (n>max) max = n
//        }
//    }
//    println("min=$min")
//    println("max=$max")
}