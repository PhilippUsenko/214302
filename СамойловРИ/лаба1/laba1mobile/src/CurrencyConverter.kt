import kotlin.math.round
import kotlin.random.Random

class CurrencyConverter(
    public val rates: MutableMap<String, Double>,
    private val bankCommission: Double
) {
    // Конвертировать валюту с учётом комиссии
    fun convert(amount: Double, fromCurrency: String, toCurrency: String): Double {
        val fromRate = rates[fromCurrency] ?: error("Неизвестная валюта: $fromCurrency")
        val toRate = rates[toCurrency] ?: error("Неизвестная валюта: $toCurrency")

        val baseAmount = amount / fromRate
        val convertedAmount = baseAmount * toRate

        val commission = convertedAmount * bankCommission / 100
        return convertedAmount - commission
    }


    fun fluctuateBynRate() {
        val fluctuation = Random.nextDouble(-0.1, 0.1)
        val currentRate = rates["BYN"] ?: error("Курс BYN не найден")
        val newRate = round((currentRate + fluctuation) * 100) / 100
        rates["BYN"] = newRate
        println("Текущий курс BYN: $newRate")
    }
}