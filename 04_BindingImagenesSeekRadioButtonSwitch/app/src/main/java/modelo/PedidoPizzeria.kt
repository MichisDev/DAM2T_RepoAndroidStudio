package modelo

data class PedidoPizzeria(
    val nombre: String,
    val queso: Boolean,
    val cebolla: Boolean,
    val bacon: Boolean,
    val borde: String,
    val imagen: Int
)
