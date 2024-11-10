package com.example.ut03_01_encuestasqlite

import adaptador.VistaAdaptador
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import modelo.Encuesta

class Detalle : AppCompatActivity(){

    private lateinit var btVolver: Button
    private lateinit var rvEncuestas: RecyclerView
    private lateinit var llEncuestas: LinearLayout
    private lateinit var cardViewDetalle: CardView
    private lateinit var adaptador: VistaAdaptador
    // Lista de encuestas
    private var encuestas: MutableList<Encuesta> = mutableListOf()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        // Obtenemos los datos de la activity
        btVolver = findViewById(R.id.btVolver)
        rvEncuestas = findViewById(R.id.rvEncuestas)
        llEncuestas = findViewById(R.id.llEncuestas)
        cardViewDetalle = findViewById(R.id.cardViewDetalle)

        // Cofiguramos el reciclerView
        rvEncuestas.layoutManager = LinearLayoutManager(this)
        // Configurar el adaptador
        adaptador = VistaAdaptador(encuestas)
        rvEncuestas.adapter = adaptador


        // Recuperamos la encuesta pasada por el intent
        val encuesta = intent.getSerializableExtra("encuesta") as? MutableList<Encuesta>

        // Si la encuesta no es nula, la añadimos a la lista de encuestas
        if (encuesta != null) {
            encuestas.addAll(encuesta)
            adaptador.notifyDataSetChanged()
        }else{
            Toast.makeText(this, "No se ha podido recuperar la encuesta", Toast.LENGTH_SHORT).show()
        }

        // Volvemos a la activity anterior
        btVolver.setOnClickListener {
            finish()
        }


    }

}