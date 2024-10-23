package modelo

import java.io.Serializable

data class Encuesta (

    val nombre: String,
    val sistemaOperativo: String,
    val especialidades: List<String>,
    val horasEstudio: Int

): Serializable