package com.example.a04_recyclersviews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v,
                                                                             insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,
                systemBars.bottom)
            insets
        }
//        // Inicializamos la lista de cantantes
//        cantantes = arrayListOf(
//            "Billie Eilish",
//            "Dua Lipa",
//            "Harry Styles",
//            "Olivia Rodrigo",
//            "The Weeknd",
//            "Doja Cat",
//            "Bad Bunny",
//            "Taylor Swift",
//            "Rosalía",
//            "Ariana Grande"
//        )
//        // Configuramos el RecyclerView
//        recyclerView = findViewById(R.id.rvCantantes)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = AdaptadorCantantes(cantantes)

    }
}