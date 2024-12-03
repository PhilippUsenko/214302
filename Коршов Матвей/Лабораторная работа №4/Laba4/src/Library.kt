import java.time.LocalDate

class Library {
    private val books: MutableList<Book> = mutableListOf()
    private val members: MutableList<LibraryMember> = mutableListOf()
    private var _lastBookIdUsed = 0
    private var _lastLibraryMemberIdUsed = 0
    var lastBookIdUsed
        get() = _lastBookIdUsed
        set(value) {
            _lastBookIdUsed = value
        }
    var lastLibraryMemberIdUsed
        get() = _lastLibraryMemberIdUsed
        set(value) {
            _lastLibraryMemberIdUsed = value
        }

    fun getMemberById(memberId: Int): LibraryMember {
        return members.find { it.id == memberId }!!
    }

    fun getBookById(bookId: Int): Book {
        return books.find { it.id == bookId }!!
    }

    fun areLibraryMembers(): Boolean {
        return members.isNotEmpty()
    }

    fun areLibraryMembersWithRentals(): Boolean {
        return members.any(LibraryMember::hasRentals)
    }

    fun areLibraryMembersWithActiveRentals(): Boolean {
        return members.any(LibraryMember::hasActiveRentals)
    }

    fun areBooks(): Boolean {
        return books.isNotEmpty()
    }

    fun areAvailableBooks(): Boolean {
        return books.any(Book::isAvailable)
    }

    fun addBook(book: Book) {
        books.add(book)
    }

    fun registerMember(member: LibraryMember): Boolean {
        if (members.find { it.id == member.id } == null) {
            members.add(member)
            return true
        }
        return false
    }

    fun isSuchMember(id: Int): Boolean {
        return members.find { it.id == id } != null
    }

    fun isSuchBook(id: Int): Boolean {
        return books.find { it.id == id } != null
    }

    fun printLibraryMembers() {
        if (members.isEmpty())
            println("No members in library")
        else
            members.forEach { println(it) }
    }

    fun printLibraryMembersWithRentals() {
        members.filter { it.hasRentals() }.forEach { println(it) }
    }

    fun printLibraryMembersWithActiveRentals() {
        members.filter { it.hasActiveRentals() }.forEach { println(it) }
    }

    fun printBooks() {
        if (books.isEmpty())
            println("No books in library")
        else
            books.forEach { println(it) }
    }

    fun printAvailableBooks() {
        books.forEach { if (it.isAvailable) println(it) }
    }

    fun rentBook(memberId: Int, bookId: Int, rentalDate: LocalDate = LocalDate.now().minusWeeks(3)): Boolean {
        val member = members.find { it.id == memberId }
        val book = books.find { it.id == bookId }

        if (member != null && book != null) {
            val rental = Rental(book, rentalDate)
            member.rentedBooks.add(rental)
            book.isAvailable = false
            return true
        } else {
            return false
        }
    }

    fun returnBook(memberId: Int, bookId: Int, returnDate: LocalDate): Boolean {
        val member = members.find { it.id == memberId }
        val rental = member?.rentedBooks?.find { it.book.id == bookId && it.returnDate == null }

        if (member != null && rental != null) {
            rental.returnDate = returnDate
            rental.book.isAvailable = true
            return true
        } else {
            return false
        }
    }

    fun calculateFineForMember(memberId: Int, currentDate: LocalDate, finePerDay: Double = 5.0): Double {
        val member = members.find { it.id == memberId }
        return member?.rentedBooks?.sumOf { it.calculateFineForRental(currentDate, finePerDay) } ?: 0.0
    }

    fun calculateFineForLibrary(currentDate: LocalDate, finePerDay: Double = 5.0): Double {
        var sum = 0.0
        members.forEach { member ->
            sum += member.rentedBooks.sumOf {
                it.calculateFineForRental(
                    currentDate,
                    finePerDay
                )
            }
        }
        return sum
    }
}