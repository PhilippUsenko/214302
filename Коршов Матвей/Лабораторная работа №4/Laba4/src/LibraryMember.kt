data class LibraryMember(
    val id: Int,
    val name: String,
    val rentedBooks: MutableList<Rental> = mutableListOf(),
) {
    override fun toString(): String {
        return "$id: $name"
    }

    fun printRentals() {
        rentedBooks.forEach { println(it) }
    }

    fun printActiveRentals() {
        rentedBooks.forEach { if (it.returnDate == null) println(it) }
    }

    fun hasRentals(): Boolean {
        return rentedBooks.isNotEmpty()
    }

    fun hasActiveRentals(): Boolean {
        return rentedBooks.any { it.returnDate == null }
    }

}
