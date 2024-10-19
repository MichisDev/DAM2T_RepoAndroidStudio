package com.example.a04_recyclersviews

import adaptador.AdapterCantantes
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // Declaramos las variables lateinit (palabra clave para no nulos) para inicializarlas más tarde
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AdapterCantantes
    private lateinit var cantantes: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v,
                                                                             insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left, systemBars.top, systemBars.right,
                systemBars.bottom
            )
            insets
        }
        // Inicializamos la lista de cantantes
        cantantes = arrayListOf(
            "Billie Eilish",
            "Dua Lipa",
            "Harry Styles",
            "Olivia Rodrigo",
            "The Weeknd",
            "Doja Cat",
            "Bad Bunny",
            "Taylor Swift",
            "Rosalía",
            "Ariana Grande"
        )
        // Configuramos el RecyclerView
        recyclerView =
            findViewById(R.id.rvItems) // Referencia al RecyclerView en el layout items =cantantes
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AdapterCantantes(cantantes) // Pasamos la lista de cantantes al adaptador de RecyclerView


//        4. Descripción del funcionamiento:

//        1. Lista de cantantes actuales: En MainActivity, hemos añadido una lista de cantantes populares
//        actuales, como Billie Eilish, Dua Lipa, The Weeknd, Bad Bunny, etc.

//        2. Adapter y ViewHolder: El CantanteAdapter enlaza cada cantante de la lista a un TextView utilizando
//        el layout simple android.R.layout.simple_list_item_1.

//        3. Interacciones:
//        o Clic: Al hacer clic sobre un ítem de la lista, el fondo del ítem cambia a color gris claro
//        (Color.LTGRAY).
//        o Mantener pulsado: Al mantener pulsado un ítem, este es eliminado de la lista, y el
//        RecyclerView se actualiza automáticamente.

    }
}