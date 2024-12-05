package org.example

import org.example.classes.AmphibiousCar
import org.example.classes.FlyingBoat
import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val scanner = Scanner(System.`in`)
    val amphibiousCar = AmphibiousCar()
    val flyingBoat = FlyingBoat()

    while (true) {
        println(
            """
            Выберите транспортное средство:
            1. Амфибийная машина
            2. Летающая лодка
            3. Выход
            """.trimIndent()
        )
        when (scanner.nextInt()) {
            1 -> {
                println(
                    """
                    Выберите действие для амфибийной машины:
                    1. Переключиться на режим езды
                    2. Переключиться на режим плавания
                    3. Ехать
                    4. Плыть
                    5. Назад
                    """.trimIndent()
                )
                when (scanner.nextInt()) {
                    1 -> amphibiousCar.switchModeToDrive()
                    2 -> amphibiousCar.switchModeToSail()
                    3 -> amphibiousCar.drive()
                    4 -> amphibiousCar.sail()
                    5 -> continue
                }
            }

            2 -> {
                println(
                    """
                    Выберите действие для летающей лодки:
                    1. Переключиться на режим полета
                    2. Переключиться на режим плавания
                    3. Лететь
                    4. Плыть
                    5. Назад
                    """.trimIndent()
                )
                when (scanner.nextInt()) {
                    1 -> flyingBoat.switchModeToFly()
                    2 -> flyingBoat.switchModeToSail()
                    3 -> flyingBoat.fly()
                    4 -> flyingBoat.sail()
                    5 -> continue
                }
            }

            3 -> {
                println("Выход из программы.")
                break
            }

            else -> println("Неверный выбор, попробуйте снова.")
        }
    }
}