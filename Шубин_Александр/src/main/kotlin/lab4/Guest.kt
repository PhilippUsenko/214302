package lab4

class Guest(var reservation: Reservation){

    fun addReservation(reservation: Reservation) {
        this.reservation = reservation
    }

}