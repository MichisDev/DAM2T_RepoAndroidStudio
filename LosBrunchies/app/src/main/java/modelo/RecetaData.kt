package modelo

import com.example.losbrunchies.R

object RecetaData {
    val recetas = mutableListOf(
        Receta("Receta 1","receta1"),
        Receta("Receta 2","receta2"),
        Receta("Receta 3","receta3"),
        // ... más recetas
    )
    fun obtenerRecetas(): List<Receta> {
        return recetas
    }

}