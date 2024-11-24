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

class Alimentacion : AppCompatActivity() {
    private lateinit var binding: ActivityAlimentacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlimentacionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recetas = listOf(
            Receta(R.drawable.receta1, "Receta 1"),
            Receta(R.drawable.receta2, "Receta 2"),
            Receta(R.drawable.receta3, "Receta 3"),
            // ... más recetas
        )

        val recyclerView = findViewById<RecyclerView>(R.id.rv_recetas)
        recyclerView.adapter = RecetaAdapter(recetas)
        // Usar el LinearLayoutManager personalizado
        recyclerView.layoutManager =
            CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Agregar el RecyclerView.ItemDecoration
        recyclerView.addItemDecoration(EspacioEntreElementos(2)) // 8dp de espacio

        // SnapHelper para que el RecyclerView se mueva a la receta seleccionada
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)

        // Configurar el botón de volver al home
        binding.btVol.setOnClickListener {
            finish()
        }

    }
}