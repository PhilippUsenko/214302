
import adapters.ShapeAdapter
import shapes.Circle
import shapes.Rectangle
import shapes.Shape
import shapes.Triangle

fun main() {

    var num = getValue("Введите радиус круга:")
    var num2:Double
    val circle: Shape = Circle(num)
    val circleAdapter = ShapeAdapter(circle)
    println("Площадь круга: ${circleAdapter.getArea()}")
    println("Периметр круга: ${circleAdapter.getPerimeter()}")
    circleAdapter.getDetails()
    num=getValue("Введите ширину прямоугольника:")
    num2=getValue("Введите длину прямоугольника:")
    val rectangle: Shape = Rectangle(num, num2)
    val rectangleAdapter = ShapeAdapter(rectangle)
    rectangleAdapter.getDetails()
    println("Площадь прямоугольника: ${rectangleAdapter.getArea()}")
    println("Периметр прямоугольника: ${rectangleAdapter.getPerimeter()}")
    num=getValue("Введите основание треугольника:")
    num2=getValue("Введите высоту треугольника:")
    val triangle: Shape = Triangle(num, num2)
    val triangleAdapter = ShapeAdapter(triangle)
    println("Площадь треугольника: ${triangleAdapter.getArea()}")
    println("Периметр треугольника: ${triangleAdapter.getPerimeter()}")
    triangleAdapter.getDetails()
}
fun getValue( message:String): Double {
    while (true) {
        try {
            print(message)
            val input = readLine()


            if (input.isNullOrBlank()) {

                throw NullPointerException("Ввод не может быть пустым.")
            }


            val num = input.toDouble()
            if(num<=0){
             throw NumberFormatException();
            }
            return num
        } catch (e: NullPointerException) {
            println(e.message)
        } catch (e: NumberFormatException) {
            println("Неправильный ввод. Введите корректное целое число.")
        }
    }
}