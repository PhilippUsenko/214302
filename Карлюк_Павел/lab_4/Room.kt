import java.time.LocalDate

class Room(
    val roomNumber: Int,
    val roomType: String,
    var pricePerNight: Double,
    var isAvailable: Boolean = true,
    private val bookings: MutableList<Reservation> = mutableListOf()
) {

    fun changeAvailability(isAvailable: Boolean) {
        this.isAvailable = isAvailable
    }

    fun isAvailable(checkInDate: LocalDate, checkOutDate: LocalDate): Boolean {

        if (bookings.isEmpty()) return true

        return bookings.none { reservation ->
            (checkInDate < reservation.checkOutDate && checkOutDate < reservation.checkInDate)
        }
    }

    fun addBooking(reservation: Reservation) {
        bookings.add(reservation)
    }

    override fun toString(): String {
        return "Номер: $roomNumber; тип номера: $roomType; стоимость: $pricePerNight"
    }
}
