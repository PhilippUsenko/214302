fun change(arr:Array<Int>){
    var r:Int
    var i=0
    val len=arr.size
    while (i<len/2){
        r = arr[i]
        arr[i]=arr[len-1-i]*-1
        arr[len-1-i]=r*-1
        i++
    }
    if(len%2!=0) arr[len/2]*=-1
}

fun main() {
    var numbers = emptyArray<Int>()
    var length = (6..16).random()
    for (x in 1..length){
        var i = (-20..20).random()
        numbers+= i;
    }
    println("\nНачальный массив:")
    for (i in numbers){
        print("$i\t")
    }
//    numbers.reverse()
//    for (i in 0..numbers.size-1){
//        numbers[i] *= -1;
//    }
    change(numbers)
    println("\n\nКонечный массив:")
    for (i in numbers){
        print("$i\t")
    }

}