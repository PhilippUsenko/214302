
fun showMenu() {
    println("==== Меню ====")
    println("1. Показать курсы валют")
    println("2. Конвертировать валюту")
    println("3. Запланировать конвертацию")
    println("4. Выйти")
    print("Выберите действие: ")
}
// Основная программа
fun main() {
    val rateLoader = LoadFiles("/Users/ruslansamojlov/IdeaProjects/laba1mobile/out/production/laba1mobile/rates.txt")
    val currencyRates = rateLoader.loadRates()


    val converter = CurrencyConverter(currencyRates, 2.0)

    var isRunning = true

    while (isRunning) {
        showMenu()
        when (readLine()?.trim()) {
            "1" -> {

                println("Курсы валют:")
                currencyRates.forEach { (currency, rate) ->
                    println("$currency: $rate")
                }
                println()
            }
            "2" -> {

                print("Введите сумму: ")
                val amount = readLine()?.toDoubleOrNull() ?: 0.0
                print("Из какой валюты (например, USD): ")
                val fromCurrency = readLine()?.uppercase() ?: ""
                print("В какую валюту (например, EUR): ")
                val toCurrency = readLine()?.uppercase() ?: ""

                val result = converter.convert(amount, fromCurrency, toCurrency)
                println("Результат: $amount $fromCurrency -> $result $toCurrency\n")
            }
            "3" -> {

                print("Введите сумму: ")
                val amount = readLine()?.toDoubleOrNull() ?: 0.0
                print("Из какой валюты (например, USD): ")
                val fromCurrency = readLine()?.uppercase() ?: ""
                print("В какую валюту (например, BYN): ")
                val toCurrency = readLine()?.uppercase() ?: ""

                print("Введите желаемый курс BYN: ")
                val targetRate = readLine()?.toDoubleOrNull() ?: 0.0

                while (true) {
                    converter.fluctuateBynRate()

                    val currentRate = currencyRates["BYN"] ?: error("Курс BYN не найден")

                    if (currentRate <= targetRate) {
                        println("Нужный курс достигнут! Текущий курс BYN: $currentRate")
                        val result = converter.convert(amount, fromCurrency, toCurrency)
                        println("Конвертация завершена: $amount $fromCurrency -> $result $toCurrency")
                        break
                    }
                    Thread.sleep(1000)
                }
            }
            "4" -> {

                println("Выход...")
                isRunning = false
            }
            else -> {
                println("Неверный ввод. Пожалуйста, выберите действие из меню.\n")
            }
        }
    }
}