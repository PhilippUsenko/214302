import java.time.LocalDate
import java.time.temporal.ChronoUnit

class Reservation(
    val id: Int,
    val guest: Guest,
    val room: Room,
    var checkInDate: LocalDate,
    var checkOutDate: LocalDate,
    var additionalServices: List<Service> = listOf()
) {
    fun addService(service: Service) {
        additionalServices += service
    }

    fun calculateTotalCost(): Double {
        val daysStayed = ChronoUnit.DAYS.between(checkInDate, checkOutDate)
        val serviceCost = additionalServices.sumOf { it.price }
        return (daysStayed * room.pricePerNight) + serviceCost
    }

    fun changeDates(newCheckInDate: LocalDate, newCheckOutDate: LocalDate) {
        checkInDate = newCheckInDate
        checkOutDate = newCheckOutDate
    }
}
