/*Создайте программу, которая реализует рекурсивные лямбда-выражения для работы с большими данными и оптимизирует их с
использованием хвостовой рекурсии.*/
/*Хвостовая-любой рекурсивный вызов является последней операцией перед возвратом из функции*/
/*позволяет избежать переполнение стека*/
fun main() {
    val a={ a:Int,  d:Int-> a+d}
    println(a(1,3))
    val largeData = List(1_000_000) { it + 1 }

    // Рекурсивное лямбда-выражение для подсчета суммы
        //val sum = sumLambda(largeData)
   //println("Сумма элементов: $sum")

    // Хвостовая рекурсия для подсчета суммы
    tailrec fun sumTailRec(data: List<Int>, index: Int, acc: Int): Int {
        return if (index >= data.size) acc
        else sumTailRec(data, index + 1, acc + data[index])
    }

    val sumOpt = sumTailRec(largeData, 0, 0)
    println("Сумма элементов c оптимизацией: $sumOpt")
}

val sumLambda: (List<Int>) -> Int by lazy { //lazy-инициализировано при 1 обращении
    { data ->
        if (data.isEmpty()) 0
        else data.first() + sumLambda(data.drop(1))
    }
}