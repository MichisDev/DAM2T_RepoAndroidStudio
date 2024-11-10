package modelo

import java.io.Serializable

class Encuesta (
    val nombre: String,
    val anonimo: Boolean,
    val SO: String,
    val especialidad: List<String>,
    val horasEstudio: Int

): Serializable