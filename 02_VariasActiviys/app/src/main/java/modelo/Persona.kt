package modelo

import java.io.Serializable

data class Persona( val nombre: String, val edad: String): Serializable{
    override fun toString(): String {
        return "Persona(nombre='$nombre', edad='$edad')"
    }
}
