package com.example.a00_inflateestiloscodigoinicial

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    /*
    1. override fun onCreate(savedInstanceState: Bundle?)
    Este es un método que se sobreescribe cuando heredas de la clase Activity o
    AppCompatActivity en Android. Se ejecuta cuando la actividad está siendo creada por
    primera vez, lo que significa que es el primer método que se llama en el ciclo de vida de
    la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        /*
        El parámetro de entrada savedInstance de create() es un objeto de tipo Bundle, el cual
        recibe datos externos que pueden ser de utilidad para el comportamiento de la Actividad.
        A menudo se usa para coordinar el cambio de posición de pantalla
        de Landscape a Portrait o viceversa.
        savedInstanceState: Es un Bundle que contiene los datos del estado anterior
        de la actividad, si fue cerrada y restaurada. Si es la primera vez que se crea la
        actividad, este Bundle será null.
         */


        // 2. super.onCreate(savedInstanceState)
        super.onCreate(savedInstanceState)
        /*
        Llamar al método de la superclase es esencial, ya que asegura que el sistema Android
        maneje correctamente la creación de la actividad, como configurar los elementos
        fundamentales.
         */


        // 3. enableEdgeToEdge(
        enableEdgeToEdge()
        /*
        Este método probablemente es una función personalizada que habilita el diseño "Edge-to-Edge", es decir, hacer que la interfaz de usuario ocupe toda la pantalla, incluyendo las
        áreas debajo de la barra de estado y barra de navegación. Este tipo de UI es común en
        dispositivos más nuevos con pantallas sin bordes.
         */


        //4. setContentView(R.layout.activity_main) Este metodo se encarga de inflar el layout
        setContentView(R.layout.activity_main)

        /*
        Este método asigna el archivo de layout activity_main.xml como el diseño principal
        de la actividad. Es decir, carga la interfaz gráfica definida en XML en el archivo
        activity_main.xml y la muestra en pantalla.
         */

        /*
        Este método asigna el archivo de layout activity_main.xml como el diseño principal
        de la actividad. Es decir, carga la interfaz gráfica definida en XML en el archivo
        activity_main.xml y la muestra en pantalla.

         */

        // 5. ViewCompat.setOnApplyWindowInsetsListener
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        /*
        6. insets.getInsets(WindowInsetsCompat.Type.systemBars())
        Este método obtiene las áreas de la pantalla ocupadas por las "system bars", es decir, la
        barra de estado, la barra de navegación o cualquier otro componente del sistema que
        pueda estar ocupando parte de la pantalla.
        7. v.setPadding(systemBars.left, systemBars.top, systemBars.right,
        systemBars.bottom)
        Aquí se ajusta el padding (relleno interno) de la vista v para acomodar los "system bars",
        es decir, ajusta el contenido de la interfaz para que no sea tapado por elementos como la
        barra de estado o de navegación.
        8. insets
        El método de listener devuelve el objeto insets tal como se recibió, lo que significa que
        estás pasando los insets tal cual a la vista.

         */

        /*
        - Inflar codigo Java significa parsear uno a uno los elementos del archivo  layout.
        - Quien se escarga de este trabajo es el metodo setContentView().
        - el metodo analiza el XML y traduce a objetos cada componente, le asigna los atributos,
        establece contenedores y todas las relaciones padre e hijo necesarias. Este método lo
        usaremos en la sección onCreate() de cada actividad
         */
    }
}