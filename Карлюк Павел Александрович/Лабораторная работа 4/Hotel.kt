import java.time.LocalDate

class Hotel(private val hotelBookingSystem: HotelBookingSystem) {

    fun getAvailableRooms(checkInDate: LocalDate, checkOutDate: LocalDate) {
        hotelBookingSystem.rooms.forEach { room ->
            if (hotelBookingSystem.isRoomAvailable(room, checkInDate, checkOutDate)) {
                println("Номер: ${room.roomNumber}; тип номера: ${room.roomType}; стоимость: ${room.pricePerNight}.")
            }
        }
    }
}