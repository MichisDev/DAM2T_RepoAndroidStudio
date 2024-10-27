package com.example.ut02_01_tresenraya

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ut02_01_tresenraya.databinding.DosjugadoresBinding  // Importación correcta

class DosJugadoresActivity : AppCompatActivity() {
    private lateinit var binding: DosjugadoresBinding
    private var tablero = Array(3) { arrayOfNulls<String>(3) }
    private var simboloJugadorUno = "X" // Símbolo del Jugador 1
    private var simboloJugadorDos = "O" // Símbolo del Jugador 2
    private var turnoJugadorUno = true // Controla de quién es el turno

    // Contadores para las victorias de ambos jugadores
    private var contadorJugadorUno = 0
    private var contadorJugadorDos = 0
    private var contadorEmpates = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DosjugadoresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar contadores de victorias y empates en 0
        binding.tvJ12.text = contadorJugadorUno.toString()
        binding.tvJ22.text = contadorJugadorDos.toString()
        binding.tvEmp2.text = contadorEmpates.toString()

        // Configura el botón Volver
        binding.btVolver2.setOnClickListener {
            finish()
        }

        empezar()
    }

    private fun empezar() {
        val casillas = listOf(
            binding.ivUno2, binding.ivDos2, binding.ivTres2,
            binding.ivCuatro2, binding.ivCinco2, binding.ivSeis2,
            binding.ivSiete2, binding.ivOcho2, binding.ivNueve2
        )

        casillas.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                val row = index / 3
                val col = index % 3
                if (tablero[row][col] == null) {
                    val simboloActual = if (turnoJugadorUno) simboloJugadorUno else simboloJugadorDos
                    tablero[row][col] = simboloActual
                    imageView.setImageResource(
                        if (turnoJugadorUno) R.drawable.x_fantasma else R.drawable.o_calabaza
                    )
                    if (haGanado(simboloActual)) {
                        if (turnoJugadorUno) {
                            contadorJugadorUno++
                            resultDialog("¡Jugador 1 ha ganado!")
                        } else {
                            contadorJugadorDos++
                            resultDialog("¡Jugador 2 ha ganado!")
                        }
                        actualizarMarcadores()
                    } else if (tableroLleno()) {
                        contadorEmpates++
                        resultDialog("¡Es un empate!")
                        actualizarMarcadores()
                    }
                    // Cambiar de turno
                    turnoJugadorUno = !turnoJugadorUno
                }
            }
        }
    }

    private fun haGanado(symbol: String): Boolean {
        for (i in 0..2) {
            if (tablero[i][0] == symbol && tablero[i][1] == symbol && tablero[i][2] == symbol) return true
            if (tablero[0][i] == symbol && tablero[1][i] == symbol && tablero[2][i] == symbol) return true
        }
        if (tablero[0][0] == symbol && tablero[1][1] == symbol && tablero[2][2] == symbol) return true
        if (tablero[0][2] == symbol && tablero[1][1] == symbol && tablero[2][0] == symbol) return true
        return false
    }

    private fun tableroLleno(): Boolean {
        return tablero.all { row -> row.all { it != null } }
    }

    private fun actualizarMarcadores() {
        binding.tvJ12.text = contadorJugadorUno.toString()
        binding.tvJ22.text = contadorJugadorDos.toString()
        binding.tvEmp2.text = contadorEmpates.toString()
    }
    private fun resultDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Resultado")
        builder.setMessage(message)
        builder.setPositiveButton("Reiniciar") { _, _ -> reiniciarTablero() }
        builder.setCancelable(false)
        builder.show()
    }

    private fun reiniciarTablero() {
        tablero = Array(3) { arrayOfNulls<String>(3) }
        val casillas = listOf(
            binding.ivUno2, binding.ivDos2, binding.ivTres2,
            binding.ivCuatro2, binding.ivCinco2, binding.ivSeis2,
            binding.ivSiete2, binding.ivOcho2, binding.ivNueve2
        )
        casillas.forEach { it.setImageResource(R.drawable.p_esq) }
        turnoJugadorUno = true // Reiniciar el turno al Jugador 1
    }

}