import java.util.*

fun main() {
    do {
        playGame()
        println("Хотите сыграть еще раз? (да/нет)")
    } while (readlnOrNull()?.lowercase(Locale.getDefault()) == "да")
    println("Спасибо за игру!")
}

fun playGame() {
    val words = listOf("kotlin", "java", "python", "swift", "javascript", "scala", "ruby")
    val levels = mapOf(
        1 to "Легкий",
        2 to "Средний",
        3 to "Трудный"
    )
    println("Добро пожаловать в игру 'Угадай слово с подсказками'!")
    println("Выберите уровень сложности:")
    for ((key, value) in levels) {
        println("$key - $value")
    }
    val level = readlnOrNull()?.toIntOrNull() ?: 1
    val maxAttempts = when (level) {
        1 -> Int.MAX_VALUE
        2 -> 7
        3 -> 5
        else -> Int.MAX_VALUE
    }
    println("Количество попыток для игры: $maxAttempts")
    val selectedWord = words.random()
    var attempts = 0
    var guessedCorrectly = false
    var hint = "_".repeat(selectedWord.length)
    println("Игра началась! Слово состоит из ${selectedWord.length} букв.")

    while (attempts < maxAttempts && !guessedCorrectly) {
        println("Попытка ${attempts + 1}. Введите слово:")
        val userInput = readlnOrNull()?.lowercase(Locale.getDefault()) ?: ""
        if (userInput == selectedWord) {
            guessedCorrectly = true
            break
        } else {
            hint = provideHint(userInput, selectedWord, level, hint)
            attempts++
        }
    }

    if (guessedCorrectly) {
        println("Поздравляем! Вы угадали слово '$selectedWord' за $attempts попыток!")
    } else {
        println("К сожалению, вы не смогли угадать слово. Правильное слово было: $selectedWord")
    }
}

fun provideHint(userInput: String, selectedWord: String, level: Int, currentHint: String): String {
    val hint = StringBuilder(currentHint)
    val minLength = minOf(userInput.length, selectedWord.length)

    for (i in 0 until minLength) {
        if (userInput[i] == selectedWord[i]) {
            hint.set(i, userInput[i])
        }
    }

    when (level) {
        1 -> {
            println("Подсказка: $hint")
        }
        2 -> {
            println("Угаданы ${countMatchingLetters(userInput, selectedWord)} букв, правильные позиции: $hint")
        }
        3 -> {
            println("Угаданы ${countMatchingLetters(userInput, selectedWord)} букв.")
        }
    }

    return hint.toString()
}

fun countMatchingLetters(userInput: String, selectedWord: String): Int {
    return userInput.zip(selectedWord).count { it.first == it.second }
}
