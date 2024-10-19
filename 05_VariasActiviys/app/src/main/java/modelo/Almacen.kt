package modelo

// Comun  todas las activitys
object Almacen {
    var listaPersonas = ArrayList<Persona>()
    fun addPersona(p: Persona) {
        listaPersonas.add(p)
    }
    fun getPersona(index: Int): Persona {
        return listaPersonas[index]
    }

}