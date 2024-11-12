package modelo

import java.io.Serializable

class Encuesta (
    val id: Int = 0,
    val nombre: String,
    val SO: String,
    val especialidad: List<String>,
    val horasEstudio: Int

): Serializable