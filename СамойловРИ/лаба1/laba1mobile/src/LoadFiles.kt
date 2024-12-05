import java.io.File

class LoadFiles(private val filePath: String) {
    // Возвращаем изменяемую карту
    fun loadRates(): MutableMap<String, Double> {
        val rates = mutableMapOf<String, Double>()
        File(filePath).forEachLine { line ->
            val parts = line.split(",")
            if (parts.size == 2) {
                val currency = parts[0].trim() // Извлекаем название валюты
                val rate = parts[1].trim().toDoubleOrNull() // Преобразуем курс в Double
                if (rate != null) {
                    rates[currency] = rate // Добавляем валюту и её курс в карту
                }
            }
        }
        return rates // Возвращаем изменяемую карту
    }
}
