package lab4

class Reservation(var myRooms: MutableList<Room>?): Booking {

    override fun book(room: Room) {
        myRooms?.add(room)
    }
}