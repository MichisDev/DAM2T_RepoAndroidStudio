package com.example.ut01_04_encuestarecyclerview

import adaptador.AdaptadorEncuesta
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
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

        btVolver = findViewById(R.id.btVolver)
        llDetalleEnc = findViewById(R.id.llDetalleEnc)

        // Configurar el RecyclerView
        val recyclerView: RecyclerView = findViewById(R.id.rvRecicler)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val encuesta: Encuesta? = intent.getSerializableExtra("encuesta") as? Encuesta

        val encuestasList = listOf(encuesta)
        val adapter = AdaptadorEncuesta(encuestasList)

        recyclerView.adapter = adapter

        // Configurar el botón de volver
        btVolver.setOnClickListener {
            finish() // Cierra la actividad y vuelve a la anterior
        }
    }
}