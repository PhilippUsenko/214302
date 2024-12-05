import core.*
import userManagement.UserManager
import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val userManager = UserManager()
    userManager.loadUsersFromFile()

    var currentUser: UserAccount? = null
    while (currentUser == null) {
        println("Выберите опцию: [1] Зарегистрироваться, [2] Войти, [3] Выйти")
        when (scanner.nextInt()) {
            1 -> {
                println("Введите имя пользователя для регистрации:")
                val username = scanner.next()
                println("Выберите роль: [1] Пользователь, [2] Администратор, [3] Модератор, [4] Гость")
                val role = when (scanner.nextInt()) {
                    1 -> User()
                    2 -> Admin()
                    3 -> Moderator()
                    4 -> Guest()
                    else -> Guest()
                }
                userManager.addUser(UserAccount(username, role))
                println("Пользователь зарегистрирован")
            }
            2 -> {
                println("Введите имя пользователя для входа:")
                val username = scanner.next()
                currentUser = userManager.authenticate(username)
                if (currentUser != null) {
                    println("Добро пожаловать, ${currentUser.username}!")
                } else {
                    println("Пользователь не найден")
                }
            }
            3 -> return
            else -> println("Неверная опция")
        }
    }

    while (true) {
        when (currentUser?.role?.name) {
            "Администратор" -> {
                println("Выберите опцию: [1] Добавить пользователя, [2] Удалить пользователя, [3] Список пользователей, [4] Удалить свой аккаунт, [5] Выйти")
                when (scanner.nextInt()) {
                    1 -> {
                        println("Введите имя пользователя:")
                        val username = scanner.next()
                        println("Выберите роль: [1] Пользователь, [2] Администратор, [3] Модератор, [4] Гость")
                        val role = when (scanner.nextInt()) {
                            1 -> User()
                            2 -> Admin()
                            3 -> Moderator()
                            4 -> Guest()
                            else -> Guest()
                        }
                        userManager.addUser(UserAccount(username, role))
                        println("Пользователь добавлен")
                    }
                    2 -> {
                        println("Введите имя пользователя для удаления:")
                        val username = scanner.next()
                        if (username != currentUser.username && userManager.findUser(username)?.role !is Moderator) {
                            userManager.removeUser(username)
                            println("Пользователь удален")
                        } else {
                            println("Нельзя удалить себя или модератора")
                        }
                    }
                    3 -> {
                        println("Список пользователей:")
                        userManager.listUsers().filter {
                            it.username != currentUser!!.username && it.role !is Moderator
                        }.forEach { println(it) }
                    }
                    4 -> {
                        userManager.removeUser(currentUser.username)
                        println("Ваш аккаунт удален")
                        break
                    }
                    5 -> break
                    else -> println("Неверная опция")
                }
            }
            "Пользователь" -> {
                println("Выберите опцию: [1] Список пользователей, [2] Удалить свой аккаунт, [3] Выйти")
                when (scanner.nextInt()) {
                    1 -> {
                        println("Список пользователей:")
                        userManager.listUsers().filter {
                            it.username != currentUser!!.username
                        }.forEach { println(it) }
                    }
                    2 -> {
                        userManager.removeUser(currentUser.username)
                        println("Ваш аккаунт удален")
                        break
                    }
                    3 -> break
                    else -> println("Неверная опция")
                }
            }
            "Модератор" -> {
                println("Выберите опцию: [1] Добавить пользователя, [2] Удалить пользователя, [3] Изменить роль, [4] Список пользователей, [5] Удалить свой аккаунт, [6] Выйти")
                when (scanner.nextInt()) {
                    1 -> {
                        println("Введите имя пользователя:")
                        val username = scanner.next()
                        println("Выберите роль: [1] Пользователь, [2] Администратор, [3] Модератор, [4] Гость")
                        val role = when (scanner.nextInt()) {
                            1 -> User()
                            2 -> Admin()
                            3 -> Moderator()
                            4 -> Guest()
                            else -> User()
                        }
                        userManager.addUser(UserAccount(username, role))
                        println("Пользователь добавлен")
                    }
                    2 -> {
                        println("Введите имя пользователя для удаления:")
                        val username = scanner.next()
                        if (username != currentUser.username) {
                            userManager.removeUser(username)
                            println("Пользователь удален")
                        } else {
                            println("Нельзя удалить себя")
                        }
                    }
                    3 -> {
                        println("Введите имя пользователя для изменения роли:")
                        val username = scanner.next()
                        println("Выберите новую роль: [1] Пользователь, [2] Администратор, [3] Модератор, [4] Гость")
                        val newRole = when (scanner.nextInt()) {
                            1 -> User()
                            2 -> Admin()
                            3 -> Moderator()
                            4 -> Guest()
                            else -> Guest()
                        }
                        if (username != currentUser.username) {
                            userManager.changeUserRole(username, newRole)
                            println("Роль пользователя изменена")
                        } else {
                            println("Нельзя изменить свою роль")
                        }
                    }
                    4 -> {
                        println("Список пользователей:")
                        userManager.listUsers().filter {
                            it.username != currentUser!!.username
                        }.forEach { println(it) }
                    }
                    5 -> {
                        userManager.removeUser(currentUser.username)
                        println("Ваш аккаунт был удален.")
                        currentUser = null
                        break
                    }
                    6 -> break
                    else -> println("Неверная опция")
                }
            }
            "Гость" -> {
                println("Выберите опцию: [1] Удалить свой аккаунт, [2] Выйти")
                when (scanner.nextInt()) {
                    1 -> {
                        userManager.removeUser(currentUser.username)
                        println("Ваш аккаунт был удален.")
                        currentUser = null
                        break
                    }
                    2 -> break
                    else -> println("Неверная опция")
                }
            }
            else -> println("Неизвестная роль пользователя")
        }
    }
}

