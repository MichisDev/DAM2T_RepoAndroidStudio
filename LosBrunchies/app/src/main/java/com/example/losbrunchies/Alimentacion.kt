package com.example.losbrunchies

import adaptador.RecetaAdapter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.losbrunchies.databinding.ActivityAlimentacionBinding
import modelo.Receta
import modelo.RecetaData

class Alimentacion : AppCompatActivity() {

    private lateinit var binding: ActivityAlimentacionBinding
    private lateinit var recetas: MutableList<Receta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAlimentacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // inicia
        recetas = RecetaData.obtenerRecetas().toMutableList()

        var recyclerView: RecyclerView = binding.rvRecetas
        recyclerView.layoutManager = CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val adapter = RecetaAdapter(recetas)
        recyclerView.adapter = adapter

        // Configurar el botón de volver al home
        binding.btVol.setOnClickListener {
            finish()
        }

    }
}