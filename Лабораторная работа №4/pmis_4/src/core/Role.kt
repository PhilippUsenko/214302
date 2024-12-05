package core

import java.io.Serializable

interface Role : Serializable {
    val name: String
}

class User : Role {
    override val name = "Пользователь"
}

class Admin : Role {
    override val name = "Администратор"
}

class Guest : Role {
    override val name = "Гость"
}

class Moderator : Role {
    override val name = "Модератор"
}
