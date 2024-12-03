package lab4

var reservation = Reservation(mutableListOf())
var guest: Guest = Guest(reservation)

fun main() {

    val listOfHotelRooms = mutableListOf(
        Room(11110, "стандартный", 1, 120.0, 0, mutableListOf()),
        Room(11111, "улучшенный/супериор", 2, 175.5, 0, mutableListOf()),
        Room(11112, "со спальной комнатой", 2, 200.75, 0, mutableListOf()),
        Room(11113, "люкс", 2, 310.0,0, mutableListOf()),
        Room(11114, "люкс", 5, 410.5,0, mutableListOf()))

    println("Добро пожаловать в систему бронирования мест в отеле!!!")
    userMenu(listOfHotelRooms)
}

fun userMenu(listOfHotelRooms: MutableList<Room>){

    while(true) {

        println(
                    "\nВыберите пункт меню:" +
                    "\n1. Забронировать номер\n2. Мои номера" +
                    "\n3. Изменить бронь" +
                    "\n4. Просмотр стоимости бронирования" +
                    "\nВЫХОД - любая другая клавиша"
        )

        val input = readlnOrNull()?.toIntOrNull()

        when (input) {
            1 -> {
                roomBooking(listOfHotelRooms)
            }
            2 ->{
                if(guest.reservation.myRooms.isNullOrEmpty()){
                    println("Вы еще не забронировали ни одного номера")
                }else{
                    for(room in guest.reservation.myRooms!!){
                        println(
                            "\n№ комнаты: ${room.roomNumb}; тип номера: ${room.roomType}; вместимость номера: ${room.capacity} +" +
                                    " чел.; стоимость (за одну ночь с учетом доп. услуг): ${room.cost} бел.руб."
                        )
                        println("Включенные дополнительные услуги:")
                        var i = 1
                        for(service in room.additionalService!!){
                            println(
                                "${i}) ${service.name}"
                            )
                            i++
                        }
                    }
                }
            }
            3 -> {
                var i = 1
                if(guest.reservation.myRooms.isNullOrEmpty()){
                    println("Вы еще не забронировали ни одного номера")
                }else {
                    alterBookings(listOfHotelRooms)
                }
            }
            4 -> {
                var generalCost = 0.0
                for (room in guest.reservation.myRooms!!){
                    generalCost += room.cost * room.nightsAmount
                }
                println("Общая стоимость проживания с учетом дополнительных услуг: ${generalCost} бел.руб.")
            }
            else -> {
                break
            }
        }
    }
}

fun roomBooking(listOfHotelRooms: MutableList<Room>){
    while (true) {
        val additionalServiceList = mutableListOf(
            AdditionalService("Не интересуют дополнительные услуги", 0.0),
            AdditionalService("Двухразовое питание в отеле (завтрак, ужин)", 50.0),
            AdditionalService("Трехразовое питание в отеле", 75.9),
            AdditionalService("Пользование сауной", 150.0),
            AdditionalService("Пользование спортивным залом", 95.5),
            AdditionalService("Пользование камерой хранения или сейфом", 30.0)
        )
        println("Доступные номера для бронирования:")
        var i = 1
        for (room in listOfHotelRooms) {
            println(
                "${i}) № комнаты: ${room.roomNumb}; тип номера: ${room.roomType}; вместимость номера: ${room.capacity}" +
                        "чел.; стоимость (за одну ночь): ${room.cost} бел.руб."
            )
            i++
        }
        val roomChoice = readlnOrNull()?.toIntOrNull()
        if (roomChoice != null) {
            if (roomChoice < 1 || roomChoice > listOfHotelRooms.size) {
                println("Ошибка ввода!!! Выберите доступный номер для бронирования")
                continue
            } else {
                while(true){
                    println("Пожалуйста, выберите интересующие дополнительные услуги" +
                            "\nДоступные доп. услуги:")
                    var j = 1
                    for (service in additionalServiceList) {
                        if(service == additionalServiceList.get(0)){
                            continue
                        }
                        println(
                            "${j}) ${service.name} (стоимость за ночь: ${service.price} бел.руб.)"
                        )
                        j++
                    }
                    println(
                        "0) ${additionalServiceList.get(0).name}"
                    )
                    val serviceChoice = readlnOrNull()?.toIntOrNull()
                    if(serviceChoice != null){
                        if (serviceChoice < 0 || serviceChoice > additionalServiceList.size - 1) {
                            println("Ошибка ввода!!! Выберите доступную дополнительную услугу")
                            continue
                        } else {
                            val bookedRoom = listOfHotelRooms.get(roomChoice - 1)
                            bookedRoom.addService(additionalServiceList.get(serviceChoice))
                            additionalServiceList.remove(additionalServiceList.get(serviceChoice))
                            print("Желаете добавить другие доп. услуги?\n1.Да\n НЕТ - любая другая клавиша\n")
                            val input = readlnOrNull()?.toIntOrNull()
                            if(input != 1){
                                reservation.book(bookedRoom)
                                listOfHotelRooms.remove(listOfHotelRooms.get(roomChoice - 1))
                                break
                            }
                        }
                    }
                }
                while (true) {
                    try {
                        println("\nВведите желаемое количество ночей, на которое хотите забронировать выбранный номер: ")
                        val input = readlnOrNull() ?: throw IllegalArgumentException("Введено пустое значение.")
                        val nightsAmount = input.toIntOrNull() ?: throw NumberFormatException("Введено не число.")
                        if (nightsAmount <= 0) {
                            throw IllegalArgumentException("Количество ночей должно быть больше нуля.")
                        }
                        reservation.myRooms!![reservation.myRooms!!.size - 1].nightsAmount = nightsAmount
                        guest.addReservation(reservation)
                        println("Вы забронировали номер на $nightsAmount ночей.")
                        break
                    } catch (e: IllegalArgumentException) {
                        println("Ошибка: ${e.message}")
                        continue
                    } catch (e: NumberFormatException) {
                        println("Ошибка: Введите действительное число.")
                        continue
                    }
                }
                print("Желаете продолжить бронирование?\n1.Да\n НЕТ - любая другая клавиша\n")
                val input = readlnOrNull()?.toIntOrNull()
                if(input != 1)break
            }
        }
    }
}

fun alterBookings(listOfHotelRooms: MutableList<Room>){
    println("Для изменения брони необходимо отменить текущую бронь и забронировать номер заново\n" +
            "Выберите номер, бронирование которого хотели бы отменить:")
    var i = 1
    for (room in guest.reservation.myRooms!!) {
        println(
            "${i}) № комнаты: ${room.roomNumb}; тип номера: ${room.roomType}; вместимость номера: ${room.capacity}" +
                    " чел.; стоимость (за одну ночь с учетом доп. услуг): ${room.cost} бел.руб."
        )
        i++
    }
    while(true) {
        try {
            var roomIndex = 0
            var flag = false
            val input = readlnOrNull() ?: throw IllegalArgumentException("Введено пустое значение.")
            val roomChoice = input.toIntOrNull() ?: throw NumberFormatException("Введено не число.")
            for (room in guest.reservation.myRooms!!) {
                if(room.roomNumb == guest.reservation.myRooms!!.get(roomChoice - 1).roomNumb){
                    flag = true
                    break
                }
                roomIndex++
            }
            if(!flag){
                println("Ошибка ввода. Невозможно изменить бронь комнаты с заданным номером")
            }
            val bookedRoomToRemove = guest.reservation.myRooms!!.get(roomIndex)
            listOfHotelRooms.add(bookedRoomToRemove)
            listOfHotelRooms.sortBy { it.roomNumb }
            guest.reservation.myRooms!!.remove(bookedRoomToRemove)
            break
        } catch (e: IllegalArgumentException) {
            println("Ошибка: ${e.message}")
            continue
        } catch (e: NumberFormatException) {
            println("Ошибка: Введите действительное число.")
            continue
        }
    }
    roomBooking(listOfHotelRooms)
}