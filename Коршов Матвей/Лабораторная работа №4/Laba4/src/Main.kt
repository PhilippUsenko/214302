import java.time.LocalDate

fun main() {

    val library = Library()

    while (true) {
        println("Choose action:\n1)Add book\n2)Register library member\n3)Add rental\n4)Remove rental\n5)Calculate total fine\n6)Print books\n7)Print library members\n8)Print library member rentals\n9)Exit")
        var choice: Int?
        try {
            choice = readlnOrNull()?.toInt()
            if (choice != null && (choice < 1 || choice > 9)) {
                println("No such option")
                continue
            }
        } catch (e: NumberFormatException) {
            println("Not a number")
            continue
        }
        when (choice) {
            null -> continue
            1 -> addBook(library)
            2 -> addLibraryMember(library)
            3 -> addRental(library)
            4 -> removeRental(library)
            5 -> calculateTotalFine(library)
            6 -> library.printBooks()
            7 -> library.printLibraryMembers()
            8 -> printMemberRentals(library)
            9 -> break
        }
    }
}

fun addBook(library: Library) {
    val id = library.lastBookIdUsed
    library.lastBookIdUsed++
    var title: String?
    while (true) {
        println("Input book title")
        title = readlnOrNull()
        if (title.isNullOrEmpty()) {
            println("Input a valid book title")
            continue
        }
        break
    }
    var author: String?
    while (true) {
        println("Input book author")
        author = readlnOrNull()
        if (author.isNullOrEmpty()) {
            println("Input a valid book author")
            continue
        }
        break
    }
    library.addBook(Book(id, title.toString(), author.toString()))
}

fun addLibraryMember(library: Library) {
    val id = library.lastLibraryMemberIdUsed
    library.lastLibraryMemberIdUsed++
    var name: String?
    while (true) {
        println("Input library member name")
        name = readlnOrNull()
        if (name.isNullOrEmpty()) {
            println("Input a library member name")
            continue
        }
        break
    }
    library.registerMember(LibraryMember(id, name.toString()))
}

fun addRental(library: Library) {
    println("Current library members:")
    if (library.areLibraryMembers())
        library.printLibraryMembers()
    else {
        println("No library members")
        return
    }
    println("Current available library books:")
    if (library.areAvailableBooks())
        library.printAvailableBooks()
    else {
        println("No available books in library")
        return
    }
    var memberId: Int
    while (true) {
        println("Choose library member id")
        try {
            val inputId = readlnOrNull()?.toInt()
            if (inputId == null || !library.isSuchMember(inputId)) {
                println("No such member")
                continue
            }
            memberId = inputId.toInt()
        } catch (e: NumberFormatException) {
            println("Not a valid id")
            continue
        }
        break
    }
    val libraryMember = library.getMemberById(memberId)
    var bookId: Int
    while (true) {
        println("Choose book")
        try {
            val inputId = readlnOrNull()?.toInt()
            if (inputId == null || !library.isSuchBook(inputId)) {
                println("No such book")
                continue
            }
            bookId = inputId.toInt()
        } catch (e: NumberFormatException) {
            println("Not a valid id")
            continue
        }
        val bookToRent = library.getBookById(bookId)
        if (!bookToRent.isAvailable) {
            println("This book isn't available")
            continue
        }
        break
    }
    library.rentBook(memberId, bookId)
    println("The book was successfully rented!")
    libraryMember.printRentals()
}

fun removeRental(library: Library) {
    println("Current library members with active rentals:")
    if (library.areLibraryMembersWithActiveRentals())
        library.printLibraryMembersWithActiveRentals()
    else {
        println("No library members with active rentals")
        return
    }
    var memberId: Int
    var libraryMember: LibraryMember
    while (true) {
        println("Choose library member id")
        try {
            val inputId = readlnOrNull()?.toInt()
            if (inputId == null || !library.isSuchMember(inputId)) {
                println("No such member")
                continue
            }
            memberId = inputId.toInt()
        } catch (e: NumberFormatException) {
            println("Not a valid id")
            continue
        }
        libraryMember = library.getMemberById(memberId)

        if (!libraryMember.hasActiveRentals()) {
            println("Not a valid member")
            continue
        }

        break
    }

    println("Chosen member active rentals:")
    libraryMember.printActiveRentals()

    var bookId: Int
    while (true) {
        println("Choose book to return")
        try {
            val inputId = readlnOrNull()?.toInt()
            if (inputId == null || libraryMember.rentedBooks.find {it.book.id == inputId && it.returnDate == null} == null) {
                println("No such book rented")
                continue
            }
            bookId = inputId.toInt()
        } catch (e: NumberFormatException) {
            println("Not a valid id")
            continue
        }
        break
    }
    library.returnBook(memberId, bookId, LocalDate.now())
    println("The book was successfully returned!")
    libraryMember.printRentals()
}

fun printMemberRentals(library: Library) {
    println("Current library members with rentals:")
    if (library.areLibraryMembersWithRentals())
        library.printLibraryMembersWithRentals()
    else {
        println("No library members with rentals")
        return
    }
    var memberId: Int
    var libraryMember: LibraryMember
    while (true) {
        println("Choose library member id")
        try {
            val inputId = readlnOrNull()?.toInt()
            if (inputId == null || !library.isSuchMember(inputId)) {
                println("No such member")
                continue
            }
            memberId = inputId.toInt()
        } catch (e: NumberFormatException) {
            println("Not a valid id")
            continue
        }
        libraryMember = library.getMemberById(memberId)

        break
    }

    println("Chosen member rentals:")
    libraryMember.printRentals()

    println("Chosen member fine: ${library.calculateFineForMember(memberId, LocalDate.now())}")
}

fun calculateTotalFine(library: Library) {
    println("Total fine for library:")
    println(library.calculateFineForLibrary(LocalDate.now()))
}