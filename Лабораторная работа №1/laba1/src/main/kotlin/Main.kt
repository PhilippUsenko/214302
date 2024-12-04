import java.io.File

fun main() {
    val fileName = "temperature_data.txt"
    val file = File(fileName)

    while (true) {
        println("Введите температуру (или '-' для завершения):")
        val input = readLine()

        if (input == "-") {
            break
        }

        // Проверка на корректность ввода числа
        val temperature = input?.toDoubleOrNull()
        if (temperature == null) {
            println("Некорректный ввод. Пожалуйста, введите число.")
            continue
        }

        println("Введите систему (C для Цельсия, F для Фаренгейта):")
        val system = readLine()!!.toUpperCase()

        if (system != "C" && system != "F") {
            println("Некорректный ввод. Пожалуйста, введите 'C' или 'F'.")
            continue
        }

        val convertedTemperature: Double

        if (system == "C") {
            convertedTemperature = celsiusToFahrenheit(temperature)
        } else {
            convertedTemperature = fahrenheitToCelsius(temperature)
        }


        println("Конвертированная температура: $convertedTemperature")

        // Запись данных в файл (только в С)
        if (system=="C"){
        file.appendText("$temperature\n")
        } else file.appendText("$convertedTemperature\n")

        // Чтение данных из файла
        val predictedTemperature : Double
        val historicalData = file.readLines().map { it.toDouble() }
        if (historicalData.size==1){
            println("Нет исторических данных. Предсказание температуры на следующий день невозможно")
        } else{
        predictedTemperature = predictNextDayTemperature(historicalData)
        println("Предсказанная температура на следующий день: $predictedTemperature")}
    }
}

fun celsiusToFahrenheit(celsius: Double): Double {
    return celsius * 9/5 + 32
}

fun fahrenheitToCelsius(fahrenheit: Double): Double {
    return (fahrenheit - 32) * 5/9
}

fun predictNextDayTemperature(historicalData: List<Double>): Double {
    // Вычисляем все разности между соседними элементами
    val differences = historicalData.zipWithNext { a, b -> b - a } // пара между соседними

    // Находим среднее значение разностей
    val averageDifference = differences.average() // average-среднее

    return historicalData.last() + averageDifference
}
