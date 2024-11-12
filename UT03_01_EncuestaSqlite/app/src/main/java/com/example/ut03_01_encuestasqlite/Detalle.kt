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
import auxiliar.Conexion
import conexion.AdminSQLiteConexion
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

        // Obtener las encuestas almacenadas en la base de datos
        val dbHelper = AdminSQLiteConexion(this)
        encuestas = Conexion.obtenerEncuestas(dbHelper) as MutableList<Encuesta>

        // Configurar el adaptador
        adaptador = VistaAdaptador(encuestas)
        rvEncuestas.adapter = adaptador


        // Volvemos a la activity anterior
        btVolver.setOnClickListener {
            finish()
        }


    }

}