class ProcessingInput {

    fun validatePhoneNumber(phoneNumber: String): Boolean {
        val phonePattern = Regex("^\\+\\d{9,15}\$")
        return phonePattern.matches(phoneNumber)
    }

    fun validateEmail(email: String): Boolean {
        val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}\$")
        return emailPattern.matches(email)
    }
}