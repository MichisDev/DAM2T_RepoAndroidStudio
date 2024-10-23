package com.example.ut01_04_encuestarecyclerview

import adaptador.AdaptadorEncuesta
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import modelo.Encuesta

class DetalleActivity : AppCompatActivity() {

    private lateinit var llDetalleEnc: LinearLayout
    private lateinit var btVolver: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detalle)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rvRecicler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        btVolver = findViewById(R.id.btVolver)
        llDetalleEnc = findViewById(R.id.llDetalleEnc)


        // Obtener la lista de encuestas del Intent
        val encuestas = intent.getSerializableExtra("encuestas") as? ArrayList<Encuesta>

        // Verificar si la lista no es nula y configurar el adaptador
        if (encuestas != null) {
            val adaptador = AdaptadorEncuesta(encuestas) // Usa la lista de encuestas obtenida
            recyclerView.adapter = adaptador
        } else {
            Toast.makeText(this, "No hay encuestas para mostrar", Toast.LENGTH_SHORT).show()
        }


        // Configurar el botón de volver
        btVolver.setOnClickListener {
            finish() // Cierra la actividad y vuelve a la anterior
        }
    }
}