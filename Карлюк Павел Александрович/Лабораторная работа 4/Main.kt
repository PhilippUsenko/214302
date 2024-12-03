import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.*

fun main() {
    val hotelSystem = HotelBookingSystem()
    val processingInput = ProcessingInput()
    val scanner = Scanner(System.`in`)
    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val room1 = Room(101, "Single", 100.0)
    val room2 = Room(102, "Double", 150.0)
    val room3 = Room(103, "Suite", 300.0)

    hotelSystem.addRoom(room1)
    hotelSystem.addRoom(room2)
    hotelSystem.addRoom(room3)

    val hotel = Hotel(hotelSystem)

    while (true) {
        println("\nСистема бронирования номеров в отеле")
        println("1. Забронировать номер")
        println("2. Отменить бронирование")
        println("3. Исправить бронирование")
        println("4. Рассчитать итоговую стоимость")
        println("5. Показать текущие бронирования")
        println("6. Выйти")

        val choice = scanner.nextLine()

        when (choice) {
            "1" -> {
                println("Введите свое имя:")
                val name = scanner.nextLine()

                println("Введите свою почту:")
                var email = scanner.nextLine()
                while (!processingInput.validateEmail(email)) {
                    println("Некорректный ввод. Попробуйте снова:")
                    email = scanner.nextLine()
                }

                println("Введите свой номер телефона:")
                var phoneNumber = scanner.nextLine()
                while (!processingInput.validatePhoneNumber(phoneNumber)) {
                    println("Некорректный ввод. Попробуйте снова:")
                    phoneNumber = scanner.nextLine()
                }

                val guest = Guest(name, email, phoneNumber)

                var checkInDate: LocalDate
                var checkOutDate: LocalDate

                while (true) {
                    println("Введите дату заселения в формате (dd.MM.yyyy):")
                    val input = scanner.nextLine()
                    try {
                        checkInDate = LocalDate.parse(input, dateFormatter)
                        if (checkInDate.isAfter(LocalDate.now())) break
                        println("Дата заселения должна быть позже сегодняшнего дня.")
                    } catch (e: DateTimeParseException) {
                        println("Некорректный формат даты. Введите дату в формате dd.MM.yyyy.")
                    }
                }

                while (true) {
                    println("Введите дату выселения в формате (dd.MM.yyyy):")
                    val input = scanner.nextLine()
                    try {
                        checkOutDate = LocalDate.parse(input, dateFormatter)
                        if (checkOutDate.isAfter(checkInDate)) break
                        println("Дата выселения должна быть позже даты заселения.")
                    } catch (e: DateTimeParseException) {
                        println("Некорректный формат даты. Введите дату в формате dd.MM.yyyy.")
                    }
                }

                println("Доступные номера:")
                hotel.getAvailableRooms(checkInDate, checkOutDate)

                var roomNumber = 0
                while (roomNumber == 0) {
                    println("Введите номер комнаты для бронирования:")
                    val input = scanner.nextLine()
                    try {
                        val roomNum = input.trim().toInt()
                        if (hotelSystem.validateRoomNumber(roomNum)) {
                            roomNumber = roomNum
                        } else {
                            println("Такой номер комнаты не существует.")
                        }
                    } catch (e: NumberFormatException) {
                        println("Некорректный ввод. Введите целое число.")
                    }
                }

                println("Доступные услуги:")
                ServiceCatalog.services.forEachIndexed { index, service ->
                    println("$index. ${service.name} - ${service.price}")
                }

                println("Выберите услуги (введите номера через запятую, например: 0,1 для минибара и уборки, или 'конец' для завершения):")
                val selectedServiceIds = mutableListOf<Int>()
                while (true) {
                    val input = scanner.nextLine()
                    if (input.lowercase() == "конец") break
                    selectedServiceIds.addAll(input.split(",").mapNotNull { it.trim().toIntOrNull() })
                }

                val reservation = hotelSystem.createReservation(guest, roomNumber, checkInDate.format(dateFormatter), checkOutDate.format(dateFormatter), selectedServiceIds)
                if (reservation != null) {
                    println("Бронирование успешно создано. ID: ${reservation.id}")
                } else {
                    println("Ошибка в создании бронирования.")
                }
            }
            "2" -> {
                var reservationId = 0
                while (reservationId == 0) {
                    println("Введите номер бронирования для отмены:")
                    val input = scanner.nextLine()
                    try {
                        reservationId = input.toInt()
                    } catch (e: NumberFormatException) {
                        println("Некорректный ввод. Введите целое число.")
                    }
                }
                hotelSystem.cancelReservationById(reservationId)
            }
            "3" -> {

                var reservationId = 0
                while (reservationId == 0) {
                    println("Введите номер бронирования для исправления:")
                    val input = scanner.nextLine()
                    try {
                        reservationId = input.toInt()
                    } catch (e: NumberFormatException) {
                        println("Некорректный ввод. Введите целое число.")
                    }
                }
                println("Введите новую дату заселения в формате (dd.MM.yyyy):")
                val newCheckIn = scanner.nextLine()
                println("Введите новую дату выселения в формате (dd.MM.yyyy):")
                val newCheckOut = scanner.nextLine()
                hotelSystem.modifyReservationById(reservationId, newCheckIn, newCheckOut)
            }
            "4" -> {
                var reservationId = 0
                while (reservationId == 0) {
                    println("Введите номер бронирования для расчета итоговой стоимости:")
                    val input = scanner.nextLine()
                    try {
                        reservationId = input.toInt()
                    } catch (e: NumberFormatException) {
                        println("Некорректный ввод. Введите целое число.")
                    }
                }
                val totalCost = hotelSystem.calculateTotalCostForReservationId(reservationId)
                println("Итоговая стоимость бронирования с ID $reservationId: $totalCost")
            }
            "5" -> {
                hotelSystem.showCurrentReservations()
            }
            "6" -> {
                println("Выход из системы.")
                return
            }
            else -> {
                println("Некорректный ввод. Пожалуйста, выберите один из вариантов.")
            }
        }
    }
}
