package com.example.ut02_01_tresenraya

import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

import com.example.ut02_01_tresenraya.databinding.UnjugadorBinding
import kotlin.random.Random

class UnJugadorActivity : AppCompatActivity() {

    // Declaración de variables
    private lateinit var binding: UnjugadorBinding
    private var tablero = Array(3) { arrayOfNulls<String>(3) } // Tablero de juego
    private var simboloJugador = "X"  // Símbolo del jugador (fantasma)
    private var simboloMaquina = "O"  // Símbolo de la máquina (calabaza)

    // Variables para llevar el conteo de partidas ganadas y empates
    private var contadorJugador = 0
    private var contadorMaquina = 0
    private var contadorEmpates = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UnjugadorBinding.inflate(layoutInflater)  // Crea la instancia correcta
        setContentView(binding.root)

        // Inicializar contadores en 0 al inicio
        binding.tvJ1.text = contadorJugador.toString()
        binding.tvJ2.text = contadorMaquina.toString()
        binding.tvEmp.text = contadorEmpates.toString()


        // Configurar el botón de volver
        binding.btVolver.setOnClickListener {
            finish()  // Cierra la actividad actual y vuelve a la actividad anterior
        }
        empezar()

    }

    private fun empezar() {
        // Asocia cada ImageView a una posición en el tablero
        val casillas = listOf(
            binding.ivUno, binding.ivDos, binding.ivTres,
            binding.ivCuatro, binding.ivCinco, binding.ivSeis,
            binding.ivSiete, binding.ivOcho, binding.ivNueve
        )

        // Se recorre cada ImageView y se le asigna un OnClickListener
        casillas.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {

                // Se obtiene la fila y columna del tablero
                val row = index / 3
                val col = index % 3

                // Si la celda está vacía, se coloca el símbolo del jugador, esto asegura que solo se pueda jugar una vez en cada celda
                if (tablero[row][col] == null) {
                    // Se coloca el símbolo del jugador en el tablero
                    tablero[row][col] = simboloJugador
                    imageView.setImageResource(R.drawable.x_fantasma)

                    // compruebo si ha ganado el jugador
                    if (haGanado(simboloJugador)) {

                        contadorJugador++
                        actualizarContadores()
                        resultDialog("¡Ganaste!")

                    } else if (tableroLleno()) {
                        contadorEmpates++
                        actualizarContadores()
                        resultDialog("Es un empate!")

                    } else {
                        // Espera de 1 segundo antes de ejecutar el turno de la máquina
                        Handler(Looper.getMainLooper()).postDelayed({

                            turnoMaquina()// Ejecuta el turno de la máquina

                        }, 2000) // 2000 milisegundos = 2 segundo
                    }
                }
            }
        }
    }

    private fun turnoMaquina() {
        // Almacena las posiciones vacías en una lista
        val casillaVacias = mutableListOf<Pair<Int, Int>>()

        // Recorre el tablero para encontrar las casillas vacías
        for (i in 0..2) {
            for (j in 0..2) {
                if (tablero[i][j] == null) {
                    casillaVacias.add(Pair(i, j))
                }
            }
        }

        // Comprueba si hay casillas vacías, si no, termina el juego
        if (casillaVacias.isNotEmpty()) {
            val (row, col) = casillaVacias[Random.nextInt(casillaVacias.size)]
            tablero[row][col] = simboloMaquina
            posicionImagenView(row, col).setImageResource(R.drawable.o_calabaza)

            if (haGanado(simboloMaquina)) {
                contadorMaquina++
                actualizarContadores()
                resultDialog("¡La máquina ganó!")
            } else if (tableroLleno()) {
                contadorEmpates++
                actualizarContadores()
                resultDialog("Es un empate!")
            }
        }
    }

    private fun haGanado(simbolo: String): Boolean {
        // Comprueba si el jugador ha ganado
        for (i in 0..2) {
            // Comprueba filas
            if (tablero[i][0] == simbolo && tablero[i][1] == simbolo && tablero[i][2] == simbolo) return true
            // Comprueba columnas
            if (tablero[0][i] == simbolo && tablero[1][i] == simbolo && tablero[2][i] == simbolo) return true
        }
        // Comprueba diagonal principal
        if (tablero[0][0] == simbolo && tablero[1][1] == simbolo && tablero[2][2] == simbolo) return true
        // Comprueba diagonal secundaria
        if (tablero[0][2] == simbolo && tablero[1][1] == simbolo && tablero[2][0] == simbolo) return true
        return false
    }

    private fun tableroLleno(): Boolean {
        return tablero.all { row -> row.all { it != null } }
    }

    private fun posicionImagenView(row: Int, col: Int): ImageView {
        return when (3 * row + col) {
            0 -> binding.ivUno
            1 -> binding.ivDos
            2 -> binding.ivTres
            3 -> binding.ivCuatro
            4 -> binding.ivCinco
            5 -> binding.ivSeis
            6 -> binding.ivSiete
            7 -> binding.ivOcho
            8 -> binding.ivNueve
            else -> throw IllegalArgumentException("Posición inválida")
        }
    }

    private fun resultDialog(message: String) {

        // Crea un cuadro de diálogo con el mensaje de resultado
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Resultado")
        builder.setMessage(message)
        builder.setPositiveButton("Reiniciar") { _, _ -> reiniciarTablero() }
        builder.setCancelable(false)
        builder.show()

    }

    private fun reiniciarTablero() {
        tablero = Array(3) { arrayOfNulls<String>(3) }
        val cells = listOf(
            binding.ivUno, binding.ivDos, binding.ivTres,
            binding.ivCuatro, binding.ivCinco, binding.ivSeis,
            binding.ivSiete, binding.ivOcho, binding.ivNueve
        )
        cells.forEach { it.setImageResource(R.drawable.p_esq) }  // Resetea a la imagen inicial
    }

    private fun actualizarContadores() {
        binding.tvJ1.text = contadorJugador.toString()
        binding.tvJ2.text = contadorMaquina.toString()
        binding.tvEmp.text = contadorEmpates.toString()
    }

}