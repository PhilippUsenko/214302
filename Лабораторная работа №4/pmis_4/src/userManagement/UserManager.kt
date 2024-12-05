package userManagement

import core.*
import java.io.*

class UserManager {
    private val users = mutableListOf<UserAccount>()

    fun addUser(user: UserAccount) {
        users.add(user)
        saveUsersToFile()
    }

    fun removeUser(username: String) {
        users.removeIf { it.username == username }
        saveUsersToFile()
    }

    fun findUser(username: String): UserAccount? {
        return users.find { it.username == username }
    }

    fun changeUserRole(username: String, newRole: Role) {
        val user = findUser(username)
        user?.changeRole(newRole)
        saveUsersToFile()
    }

    fun listUsers(): List<UserAccount> {
        return users
    }

    fun authenticate(username: String): UserAccount? {
        return findUser(username)
    }

    private fun saveUsersToFile() {
        ObjectOutputStream(FileOutputStream("users.dat")).use { it.writeObject(users) }
    }

    fun loadUsersFromFile() {
        if (File("users.txt").exists()) {
            ObjectInputStream(FileInputStream("users.txt")).use {
                @Suppress("UNCHECKED_CAST")
                users.addAll(it.readObject() as List<UserAccount>)
            }
        }
    }
}
