import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HotelBookingSystem {

    val rooms = mutableListOf<Room>()
    private val reservations = mutableListOf<Reservation>()
    private var nextReservationId = 1

    fun addRoom(room: Room) {
        rooms.add(room)
    }

    fun createReservation(guest: Guest, roomNumber: Int, checkIn: String, checkOut: String, selectedServices: List<Int> = listOf()): Reservation? {
        val room = rooms.find { it.roomNumber == roomNumber }
        val checkInDate = localDateConvert(checkIn)
        val checkOutDate = localDateConvert(checkOut)

        if (room != null && isRoomAvailable(room, checkInDate, checkOutDate)) {
            val reservation = Reservation(nextReservationId++, guest, room, checkInDate, checkOutDate)
            selectedServices.forEach { serviceId ->
                val service = ServiceCatalog.services.getOrNull(serviceId)
                if (service != null) {
                    reservation.addService(service)
                }
            }
            reservations.add(reservation)
            return reservation
        } else {
            println("Комната $roomNumber занята на указанные даты.")
            return null
        }
    }

    private fun localDateConvert(date: String): LocalDate {
        return try {
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            LocalDate.parse(date, formatter)
        } catch (e: Exception) {
            e.printStackTrace()
            LocalDate.now()
        }
    }

    fun isRoomAvailable(room: Room, checkInDate: LocalDate, checkOutDate: LocalDate): Boolean {
        return reservations.none { reservation ->
            reservation.room == room &&
                    (checkInDate < reservation.checkOutDate && checkOutDate > reservation.checkInDate)
        }
    }


    fun cancelReservationById(reservationId: Int) {
        val reservation = reservations.find { it.id == reservationId }
        if (reservation != null) {
            reservation.room.changeAvailability(true)
            reservations.remove(reservation)
            println("Бронирование с ID $reservationId отменено.")
        } else {
            println("Бронирование с ID $reservationId не найдено.")
        }
    }

    fun modifyReservationById(reservationId: Int, newCheckIn: String, newCheckOut: String) {
        val reservation = reservations.find { it.id == reservationId }
        if (reservation != null) {
            val newCheckInDate = localDateConvert(newCheckIn)
            val newCheckOutDate = localDateConvert(newCheckOut)

            if (newCheckInDate.isBefore(newCheckOutDate)) {
                reservation.changeDates(newCheckInDate, newCheckOutDate)
                println("Бронирование с ID $reservationId изменено.")
            } else {
                println("Ошибка: дата заселения не может быть позже даты выселения.")
            }
        } else {
            println("Бронирование с ID $reservationId не найдено.")
        }
    }

    fun calculateTotalCostForReservationId(reservationId: Int): Double {
        val reservation = reservations.find { it.id == reservationId }
        return reservation?.calculateTotalCost() ?: 0.0
    }

    fun showCurrentReservations() {
        if (reservations.isEmpty()) {
            println("Нет текущих бронирований.")
        } else {
            println("Текущие бронирования:")
            reservations.forEach { reservation ->
                val servicesList = reservation.additionalServices.joinToString(", ") { it.name }
                println("ID: ${reservation.id}, Гость: ${reservation.guest.name}, Номер комнаты: ${reservation.room.roomNumber}, Даты: ${reservation.checkInDate}, ${reservation.checkOutDate}, Доп. услуги: $servicesList")
            }
        }
    }
    fun validateRoomNumber(num: Int): Boolean {
        rooms.forEach { room ->
            if (room.roomNumber == num) {
                return true
            }
        }
        return false
    }


    override fun toString(): String {
        rooms.forEach { println(it.toString()) }
        return ""
    }
}
