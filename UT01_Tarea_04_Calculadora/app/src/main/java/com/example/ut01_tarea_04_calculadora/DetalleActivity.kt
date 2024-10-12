package com.example.ut01_tarea_04_calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import modelo.Encuesta

class DetalleActivity : AppCompatActivity() {

    private lateinit var tvDetallesEncuesta: TextView
    private lateinit var buttonVolver: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detalle)

        tvDetallesEncuesta = findViewById(R.id.tvDetallesEncuesta)
        buttonVolver = findViewById(R.id.btVolver)

        // Obtener la encuesta pasada desde MainActivity
        val encuesta: Encuesta? = intent.getSerializableExtra("encuesta") as? Encuesta

        // Mostrar los detalles de la encuesta
        tvDetallesEncuesta.text = encuesta?.toString() ?: "No hay detalles disponibles"

        // Configurar el botón de volver
        buttonVolver.setOnClickListener {
            finish() // Cierra la actividad y vuelve a la anterior
        }
    }
}