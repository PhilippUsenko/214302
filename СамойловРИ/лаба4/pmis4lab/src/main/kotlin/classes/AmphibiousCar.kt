package org.example.classes

import org.example.interfaces.Drivable
import org.example.interfaces.Sailable

class AmphibiousCar : Drivable, Sailable {
    private var mode: String = "Driving" // Режим по умолчанию — езда

    fun switchModeToDrive() {
        mode = "Driving"
        println("Переключено на режим езды.")
    }

    fun switchModeToSail() {
        mode = "Sailing"
        println("Переключено на режим плавания.")
    }

    override fun drive() {
        if (mode == "Driving") {
            println("Амфибийная машина едет по дороге.")
        } else {
            println("Невозможно ехать в режиме плавания! Сначала переключитесь на режим езды.")
        }
    }

    override fun sail() {
        if (mode == "Sailing") {
            println("Амфибийная машина плывет по воде.")
        } else {
            println("Невозможно плыть в режиме езды! Сначала переключитесь на режим плавания.")
        }
    }
}