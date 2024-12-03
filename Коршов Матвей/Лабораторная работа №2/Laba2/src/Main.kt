fun main() {
    val stringList = ArrayList<String>()
    var flag = true
    while (flag) {
        println("Input strings to add to list")
        while (true) {
            print("${stringList.size}: ")
            val inputString = readlnOrNull()
            if (inputString.isNullOrBlank())
                break
            stringList.add(inputString)
        }
        if (stringList.size == 0) {
            println("Empty string list")
            continue
        } else
            flag = false
    }
    flag = true
    var substringToSearch = ""
    while (flag) {
        println("Input substring to search in list above")
        val tempSubstring = readlnOrNull()
        if (tempSubstring.isNullOrBlank()) {
            println("Empty substring")
            continue
        } else {
            substringToSearch = tempSubstring
            flag = false
        }
    }
    var chosenSearchAlg: Int
    while (true) {
        println("Chose search algorithm\n1)KMP\n2)Java contains")
        try {
            val input = readlnOrNull()?.toInt()
            if (input == 1 || input == 2) {
                chosenSearchAlg = input
                break
            } else
                println("No such option")
        } catch (e: NumberFormatException) {
            println("Input is not a number")
        }
    }

    var result = "Indexes of elements with this substring: "
    var substringWasFound = false

    stringList.forEach {
        if (chosenSearchAlg == 1) {
            if (KMPSearch(substringToSearch, it).isNotEmpty()) {
                result += "${stringList.indexOf(it)} "
                substringWasFound = true
            }
        } else {
            if (it.contains(substringToSearch)) {
                result += "${stringList.indexOf(it)} "
                substringWasFound = true
            }

        }
    }

    if (substringWasFound)
        println(result)
    else
        println("No strings with such substring found")
}

fun computeLPSArray(pat: String, patLength: Int, lps: IntArray) {
    var len = 0

    lps[0] = 0

    var i = 1
    while (i < patLength) {
        if (pat[i] == pat[len]) {
            len++
            lps[i] = len
            i++
        } else {
            if (len != 0) {
                len = lps[len - 1]
            } else {
                lps[i] = 0
                i++
            }
        }
    }
}

//Алгоритм Кнута-Морриса-Пратта
fun KMPSearch(pat: String, txt: String): List<Int> {
    val patLength = pat.length
    val txtLength = txt.length

    val lps = IntArray(patLength)
    val result: MutableList<Int> = ArrayList()

    computeLPSArray(pat, patLength, lps)

    var i = 0
    var j = 0
    while ((txtLength - i) >= (patLength - j)) {
        if (pat[j] == txt[i]) {
            j++
            i++
        }

        if (j == patLength) {
            result.add(i - j)
            j = lps[j - 1]
        } else if (i < txtLength
            && pat[j] != txt[i]
        ) {
            if (j != 0) {
                j = lps[j - 1]
            } else {
                i += 1
            }
        }
    }
    return result
}