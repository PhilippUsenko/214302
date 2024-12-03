data class Book(
    private val _id: Int,
    private val _title: String,
    private val _author: String,
    private var _isAvailable: Boolean = true
) {
    val id: Int
        get() = _id
    val title: String
        get() = _title
    val author: String
        get() = _author
    var isAvailable: Boolean
        set(value) {
            _isAvailable = value
        }
        get() = _isAvailable

    override fun toString(): String {
        return "$_id: $_title by $_author; available: $_isAvailable"
    }
}