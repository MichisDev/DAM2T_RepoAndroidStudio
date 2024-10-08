package com.example.pmdm_ut01_juegodados

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // Declaración de TextViews chat
    private lateinit var tvGanador1: TextView
    private lateinit var tvGanador2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Inicializa tus TextViews chat
        tvGanador1 = findViewById(R.id.tvCampeon1)
        tvGanador2 = findViewById(R.id.tvCampeon2)
        tvGanador1.visibility = View.GONE
        tvGanador2.visibility = View.GONE

        // Insertar imagenes
        val imgJugador1 = findViewById<ImageButton>(R.id.imgJugador1)
        imgJugador1.setImageResource(R.drawable.principal)
        val imgJugador2 = findViewById<ImageButton>(R.id.imgJugador2)
        imgJugador2.setImageResource(R.drawable.principal)

        // Insertar botones
        val btJugador1 = findViewById<Button>(R.id.btJugador1)
        val btJugador2 = findViewById<Button>(R.id.btJugador2)
        val btReiniciar = findViewById<Button>(R.id.btReiniciar)

        // Insertar textos
        var tvTiradas1 = findViewById<TextView>(R.id.etnTiradasJ1)
        var tvTiradas2 = findViewById<TextView>(R.id.etnTiradasJ2)
        var etnPuntos1 = findViewById<TextView>(R.id.etnPuntos1)
        var etnPuntos2 = findViewById<TextView>(R.id.etnPuntos2)
        var etnGanada1 = findViewById<TextView>(R.id.etnGanada1)
        var etnGanada2 = findViewById<TextView>(R.id.etnGanada2)
        var ganadas1 = etnGanada1.text.toString().toInt()
        var ganadas2 = etnGanada2.text.toString().toInt()

        // Variables para el juego
        var puntosJugador1 = 0
        var puntosJugador2 = 0
        var tiradasJugador1 = 0
        var tiradasJugador2 = 0
        ganadas1 = 0
        ganadas2 = 0


        // Botón para el jugador 1
        btJugador1.setOnClickListener {
            try {
                // Lógica para el jugador 1
                if (tiradasJugador1 < 5) {
                    tiradasJugador1++
                    val dado1 = lanzarDado()
                    puntosJugador1 += dado1

                    // Actulizar la imagen del dado del jugador 1
                    imgJugador1.setImageResource(obtenerImagenDado(dado1))

                    // Actualizar el texto de las tiradas y puntos jugador 1
                    tvTiradas1.text = tiradasJugador1.toString()
                    etnPuntos1.text = puntosJugador1.toString()


                } else {
                    Toast.makeText(this, "Jugador 1 ha terminado sus tiradas", Toast.LENGTH_SHORT)
                        .show()
                }

                // Verificar si ambos jugadores han terminado para mostrar el ganador
                if (tiradasJugador1 == 5 && tiradasJugador2 == 5) {
                    mostrarGanador(puntosJugador1, puntosJugador2, etnGanada1, etnGanada2)
                    btJugador1.isEnabled = false // Desactivar botones
                    btJugador2.isEnabled = false
                }
            } catch (e: Exception) {
                Toast.makeText(this, "Ha ocurrido un error: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }

        }

        // Botón para el jugador 2
        btJugador2.setOnClickListener {
            try {
                // Lógica para el jugador 2
                if (tiradasJugador2 < 5) {

                    tiradasJugador2++
                    val dado1 = lanzarDado()
                    puntosJugador2 += dado1

                    // Actulizar la imagen del dado del jugador 2
                    imgJugador2.setImageResource(obtenerImagenDado(dado1))

                    // Actualizar el texto de las tiradas y puntos del jugador 2
                    tvTiradas2.text = tiradasJugador2.toString()
                    etnPuntos2.text = puntosJugador2.toString()

                } else {
                    Toast.makeText(this, "Jugador 2 ha terminado sus tiradas", Toast.LENGTH_SHORT)
                        .show()
                }

                // Verificar si ambos jugadores han terminado para mostrar el ganador
                if (tiradasJugador1 == 5 && tiradasJugador2 == 5) {
                    mostrarGanador(puntosJugador1, puntosJugador2, etnGanada1, etnGanada2)
                    btJugador1.isEnabled = false // Desactivar botones
                    btJugador2.isEnabled = false

                }
            } catch (e: Exception) {
                Toast.makeText(this, "Ha ocurrido un error: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
                e.printStackTrace()
            }

        }

        // Botón para reiniciar el juego

        btReiniciar.setOnClickListener {
            // Reiniciar las variables
            puntosJugador1 = 0
            puntosJugador2 = 0
            tiradasJugador1 = 0
            tiradasJugador2 = 0

            // Reiniciar las vistas de texto
            etnPuntos1.text = "0"
            etnPuntos2.text = "0"
            tvTiradas1.text = "0"
            tvTiradas2.text = "0"

            // Habilitar botones nuevamente
            btJugador1.isEnabled = true
            btJugador2.isEnabled = true

            // Ocultar los mensajes de ganador
            tvGanador1.visibility = View.GONE // Oculta el mensaje de ganador
            tvGanador2.visibility = View.GONE // Oculta el mensaje de ganador

            // Reiniciar la imagen de los dados
            imgJugador1.setImageResource(R.drawable.principal)
            imgJugador2.setImageResource(R.drawable.principal)
        }


    }

    private fun mostrarGanador(
        puntosJugador1: Int,
        puntosJugador2: Int,
        etnGanada1: TextView,
        etnGanada2: TextView
    ) {

        // Ocultar los mensajes de campeón al inicio
        tvGanador1.visibility = View.GONE
        tvGanador2.visibility = View.GONE

        if (puntosJugador1 > puntosJugador2) {
            Toast.makeText(this, "¡Jugador 1 es el campeón!", Toast.LENGTH_SHORT).show()
            tvGanador1.visibility = View.VISIBLE // Muestra el mensaje del ganador 1
            var ganadas1 = etnGanada1.text.toString().toInt()
            ganadas1++
            etnGanada1.text = ganadas1.toString()
        } else if (puntosJugador2 > puntosJugador1) {
            Toast.makeText(this, "¡Jugador 2 es el campeón!", Toast.LENGTH_SHORT).show()
            tvGanador2.visibility = View.VISIBLE // Muestra el mensaje del ganador 2
            var ganadas2 = etnGanada2.text.toString().toInt()
            ganadas2++
            etnGanada2.text = ganadas2.toString()
        } else {
            Toast.makeText(this, "¡Es un empate!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun obtenerImagenDado(dado1: Int): Int {
        // Devuelve la imagen correspondiente al dado
        return when (dado1) {
            1 -> R.drawable.uno
            2 -> R.drawable.dos
            3 -> R.drawable.tres
            4 -> R.drawable.cuatro
            5 -> R.drawable.cinco
            6 -> R.drawable.seis
            else -> R.drawable.principal
        }

    }

    private fun lanzarDado(): Int {
        val dado = (1..6).random()
        return dado
    }
}