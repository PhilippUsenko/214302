package org.example.classes

import org.example.interfaces.Flyable
import org.example.interfaces.Sailable

class FlyingBoat : Flyable, Sailable {
    private var mode: String = "Sailing" // Режим по умолчанию — плавание

    fun switchModeToFly() {
        mode = "Flying"
        println("Переключено на режим полета.")
    }

    fun switchModeToSail() {
        mode = "Sailing"
        println("Переключено на режим плавания.")
    }

    override fun fly() {
        if (mode == "Flying") {
            println("Летающая лодка летит по воздуху.")
        } else {
            println("Невозможно лететь в режиме плавания! Сначала переключитесь на режим полета.")
        }
    }

    override fun sail() {
        if (mode == "Sailing") {
            println("Летающая лодка плывет по воде.")
        } else {
            println("Невозможно плыть в режиме полета! Сначала переключитесь на режим плавания.")
        }
    }
}
