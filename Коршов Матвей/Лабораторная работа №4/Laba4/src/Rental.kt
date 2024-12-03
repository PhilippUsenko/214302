import java.time.LocalDate

data class Rental(
    private val _book: Book,
    private val _rentalDate: LocalDate,
    private var _returnDate: LocalDate? = null,
) {
    val book: Book
        get() = _book
    val rentalDate: LocalDate
        get() = _rentalDate
    var returnDate: LocalDate?
        set(value) {
            _returnDate = value
        }
        get() = _returnDate

    fun calculateFineForRental(currentDate: LocalDate, finePerDay: Double): Double {
        val dueDate = _rentalDate.plusWeeks(2)
        val overdueDate = _returnDate ?: currentDate
        if (overdueDate.isAfter(dueDate)) {
            val daysLate = overdueDate.toEpochDay() - dueDate.toEpochDay()
            return daysLate * finePerDay
        } else {
            return 0.0
        }
    }

    override fun toString(): String {
        return "$_book; rentalDate=$_rentalDate; returnDate=$_returnDate"
    }
}