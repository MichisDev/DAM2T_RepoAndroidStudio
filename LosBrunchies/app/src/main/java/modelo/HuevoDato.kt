package modelo

object HuevoDato {
    val huevo = mutableListOf(
        Huevo("Huevo 1","huevo_01"),
        Huevo("Huevo 2","huevo_02"),
        Huevo("Huevo 3","huevo_03"),
        Huevo("Huevo 3","huevo_04"),
        // ... más recetas
    )
    fun obtenerHuevo(): List<Huevo> {
        return huevo
    }
}