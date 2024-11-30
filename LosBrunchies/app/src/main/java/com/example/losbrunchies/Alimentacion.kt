package com.example.losbrunchies

import adaptador.RecetaAdapter
import android.os.Bundle
import android.view.WindowManager
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.losbrunchies.databinding.ActivityAlimentacionBinding
import com.example.losbrunchies.databinding.CrearRecetaBinding
import modelo.Receta
import modelo.RecetaData

class Alimentacion : AppCompatActivity() {

    private lateinit var binding: ActivityAlimentacionBinding
    private lateinit var recetas: MutableList<Receta>
    // Agregar binding para el diálogo
    private lateinit var dialogBinding: CrearRecetaBinding
    // combobox
    private lateinit var tvCombobox: AutoCompleteTextView


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

        // Configurar el boton de crear alert dialog
        binding.ivCrear.setOnClickListener {
            mostrarCrearRecetaDialog()
        }


        // Configurar el botón de volver al home
        binding.ivVolverAli.setOnClickListener {
            finish()
        }

    }

    private fun mostrarCrearRecetaDialog() {
        val builder = AlertDialog.Builder(this)
        dialogBinding = CrearRecetaBinding.inflate(layoutInflater) // Inflar layout del diálogo con binding
        builder.setView(dialogBinding.root)

        val dialog = builder.create()
        dialog.show()

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        // Acceder a los elementos y manejar interacciones usando dialogBinding
        // dialogBinding.tvNombreRec.text
        // ...



    }
}