package com.example.ut02_02_simondice

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ut02_02_simondice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val secuenciaSimon = ArrayList<Int>()
    private var secuenciaJugador = ArrayList<Int>()
    private var nivel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // Mostrar el nivel en la pantalla
        binding.tvNivel.text = nivel.toString()

        // Configurar el comportamiento de los botones (imagenes)
        binding.ivUno.setOnClickListener {
            comprobarSecuencia(1)
        }
        binding.ivDos.setOnClickListener {
            comprobarSecuencia(2)
        }
        binding.ivTres.setOnClickListener {
            comprobarSecuencia(3)
        }
        binding.ivCuatro.setOnClickListener {
            comprobarSecuencia(4)}
        
        empezarRonda()


    }

    private fun comprobarSecuencia(Color: Int) {

        // Añade el color pulsado por el jugador representado como un número entero a la secuencia del jugador
        secuenciaJugador.add(Color)

        // Comprueba si la secuencia del jugador es correcta
        if (secuenciaJugador[secuenciaJugador.size - 1] != secuenciaSimon[secuenciaJugador.size - 1]) {
            // La secuencia del jugador es incorrecta, reiniciar el juego
            nivel = 0
            empezarRonda()
        }else  {
            // La secuencia del jugador es correcta, continuar con la siguiente ronda
            if (secuenciaJugador.size == secuenciaSimon.size) {
                nivel++
                actulizarNivel()
                empezarRonda()
            }
        }

    }

    private fun actulizarNivel() {
        binding.tvNivel.text = "$nivel"
    }

    private fun empezarRonda() {

        // Limpia las secuencias del jugador
        secuenciaJugador.clear();
        // añade un nuevo paso a la secuencia del simon
        secuenciaSimon.add((1..4).random())
        empezarSecuenciaSimon()
    }

    /*
    El Handler en Android se utiliza para programar tareas que se ejecutarán después de un cierto período de tiempo.
     */
    private fun empezarSecuenciaSimon() {

        // Actua como un temporizador
        val handler = Handler()
        var retraso = 500L // tiempo de espera inicial 0,5

        for(color in secuenciaSimon){
            // postDelayed permite que la acción se ejecute después de un retraso de tiempo
            handler.postDelayed({
                iluminarColor(color)
            }, retraso)
            retraso += 700

        }
    }
    private fun iluminarColor(color: Int) {

        when (color) {
            1 -> binding.ivUno.setBackgroundColor(Color.RED)
            2 -> binding.ivDos.setBackgroundColor(Color.BLUE)
            3 -> binding.ivTres.setBackgroundColor(Color.YELLOW)
            4 -> binding.ivCuatro.setBackgroundColor(Color.GREEN)

        }

        // Vuelve a los colores originales después de un breve parpadeo
        Handler().postDelayed({
            binding.ivUno.setBackgroundColor(Color.TRANSPARENT)
            binding.ivDos.setBackgroundColor(Color.TRANSPARENT)
            binding.ivTres.setBackgroundColor(Color.TRANSPARENT)
            binding.ivCuatro.setBackgroundColor(Color.TRANSPARENT)
        }, 400)
    }
}