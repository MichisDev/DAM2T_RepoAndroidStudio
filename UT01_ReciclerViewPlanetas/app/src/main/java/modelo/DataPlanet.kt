package modelo

object DataPlanet {

    object PlanetData {

        // Lista de objetos de planeta para la clase planeta
        private val planets = mutableListOf(
            Planet("Mercurio", 4879, 0.39, "mercurio"),
            Planet("Venus", 12104, 0.72, "venus"),
            Planet("Tierra", 12756, 1.00, "tierra"),
            Planet("Marte", 6792, 1.52, "marte"),
            Planet("Jupiter", 142984, 5.20, "jupiter"),
            Planet("Saturno", 120536, 9.58, "saturno"),
            Planet("Urano", 51118, 19.22, "urano"),
            Planet("Neptuno", 49528, 30.05, "neptuno")
        )
        // Funcion que obtiene los planetas
        fun getPlanets(): MutableList<Planet> {
            return planets
        }
    }
}