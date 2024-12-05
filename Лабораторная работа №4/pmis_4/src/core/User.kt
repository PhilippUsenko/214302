package core

import java.io.Serializable

class UserAccount(val username: String, var role: Role) : Serializable {
    fun changeRole(newRole: Role) {
        role = newRole
    }

    override fun toString(): String {
        return "Логин : '$username'\nРоль : ${role.name})"
    }
}
