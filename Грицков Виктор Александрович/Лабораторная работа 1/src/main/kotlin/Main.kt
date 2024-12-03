import java.io.File
import org.knowm.xchart.SwingWrapper
import org.knowm.xchart.XYChartBuilder
import org.knowm.xchart.style.markers.SeriesMarkers
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

fun main() {
    var isAlive = true

    println("Данная программа предназначена для расчета ИМТ ")

    while (isAlive) {
        println("1. Рассчитать ИМТ \n" +
                "2. Вывести график \n" +
                "3. Вывести данные из файла \n")
        print("Выберите действие: ")

        val input = readLine()?.toIntOrNull()

        if (input != null) {
            try {
                when (input) {
                    1 -> inputData()
                    2 -> drawGraph()
                    3 -> readFromFile()
                    else -> {
                        println("Неверный выбор. Программа завершает работу...")
                        isAlive = false
                    }
                }
            } catch (e: NumberFormatException) {
                println("Ошибка: Ввод должен быть числом. Пожалуйста, попробуйте снова.")
            }
        } else {
            println("Ошибка: Ввод не может быть пустым. Пожалуйста, попробуйте снова.")
        }
    }
}


fun calculate_BMI(mass: Double, height: Double): Double{
    return mass/(height * height)
}
fun analize_BMI(bmi: Double){
    when {
        bmi < 18.5 -> println("Недостаточный вес")
        bmi in 18.5..24.9 -> println("Нормальный вес")
        bmi in 25.0..29.9 -> println("Избыточный вес")
        else -> println("Ожирение")
    }
}

fun saveInFile(weight: Double, height: Double, bmi: Double) {
    val fileName = "BMI_DATA.txt"
    val data = "Вес: $weight кг. Рост: $height м. Индекс Массы Тела: %.2f\n".format(bmi)

    try {
        File(fileName).appendText(data + "\n\n")
        println("Данные успешно сохранены в файл $fileName\n\n")
    } catch (e: Exception) {
        println("Ошибка при сохранении данных: ${e.message}\n\n")
    }
}

fun readFromFile(){
    try {
        val lines = File("BMI_DATA.txt").readLines()
        if (lines.isNotEmpty()) {
            println("\n\n=== Содержимое файла BMI_DATA.txt ===\n\n")
            lines.forEach { println(it) }
            println()
        } else {
            println("Файл \"BMI_DATA.txt\" пуст.")
        }
    } catch (e: Exception) {
        println("Ошибка при чтении файла: ${e.message}")
    }
}

fun inputData(){
    var weight: Double
    var height: Double
    var result: Double

    while (true) {
        print("Введите вес (в кг): ")
        val weightInput = readLine()
        if (weightInput != null && weightInput.toDoubleOrNull() != null && weightInput.toDouble() > 0) {
            weight = weightInput.toDouble()
            break
        } else {
            println("Неверный ввод. Вес должен быть положительным!")
        }
    }

    while (true) {
        print("Введите рост (в см): ")
        val heightInput = readLine()
        if (heightInput != null && heightInput.toDoubleOrNull() != null && heightInput.toDouble() > 0) {
            height = heightInput.toDouble() / 100
            break
        } else {
            println("Неверный ввод. Рост должен быть положительным!")
        }
    }
    result = calculate_BMI(weight, height)
    println("Ваш Индекс Массы Тела: %.2f".format(result))
    analize_BMI(result)
    saveInFile(weight, height, result)
}

fun drawGraph() {
    val fileName = "BMI_DATA.txt"
    val bmiValues = mutableListOf<Double>()

    try {
        val lines = File(fileName).readLines()
        lines.forEach { line ->
            if (line.contains("Индекс Массы Тела:")) {
                val bmiString = line.substringAfter("Индекс Массы Тела: ").trim()
                try {
                    val numberFormat = NumberFormat.getInstance(Locale("ru", "RU"))
                    val bmi = numberFormat.parse(bmiString).toDouble()
                    bmiValues.add(bmi)
                } catch (e: ParseException) {
                    println("Ошибка при парсинге значения ИМТ: $bmiString")
                }
            }
        }

        if (bmiValues.isEmpty()) {
            println("Нет данных для построения графика.")
            return
        }

        val chart = XYChartBuilder().width(800).height(600).title("График ИМТ").xAxisTitle("Измерение").yAxisTitle("ИМТ").build()

        val xData = (1..bmiValues.size).map { it.toDouble() }
        val yData = bmiValues
        val series = chart.addSeries("ИМТ", xData, yData)
        series.marker = SeriesMarkers.CIRCLE

        chart.styler.yAxisMin = bmiValues.minOrNull()
        chart.styler.yAxisMax = bmiValues.maxOrNull()

        SwingWrapper(chart).displayChart()

    } catch (e: Exception) {
        println("Ошибка при построении графика: ${e.message}")
    }
}
