import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.YearMonth

fun main() {

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    while (true) {
        println("Введите дату в формате dd.MM.yyyy (или введите 'exit' для выхода):")
        val input = readLine()

        if (input.equals("exit", ignoreCase = true)) {
            println("Выход из программы.")
            break
        }

        try {
            val dateParts = input?.split(".")
            if (dateParts == null || dateParts.size != 3) {
                throw DateTimeParseException("Неверный формат даты", input, 0)
            }

            val day = dateParts[0].toInt()
            val month = dateParts[1].toInt()
            val year = dateParts[2].toInt()

            if (month !in 1..12) {
                throw DateTimeParseException("Неверный месяц", input, 0)
            }

            val yearMonth = YearMonth.of(year, month)
            val daysInMonth = yearMonth.lengthOfMonth()

            if (day !in 1..daysInMonth) {
                throw DateTimeParseException("Неверный день месяца", input, 0)
            }

            val date = LocalDate.of(year, month, day)
            val nextDate = date.plusDays(1)

            println("Следующая дата: ${nextDate.format(formatter)}")
        } catch (e: DateTimeParseException) {
            println("Некорректная дата. Попробуйте снова.")
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
    }
}
