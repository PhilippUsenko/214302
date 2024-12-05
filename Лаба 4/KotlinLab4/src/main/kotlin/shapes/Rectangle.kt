package shapes

// Класс Rectangle
class Rectangle(private val width: Double, private val height: Double) : Shape() {
    override fun getArea(): Double {
        return width * height
    }

    override fun getPerimeter(): Double {
        return 2 * (width + height)
    }

    fun getWidth(): Double {
        return width
    }

    fun getHeight(): Double {
        return height
    }
}