package com.example.losbrunchies

import adaptador.RecetaAdapter
import android.os.Bundle
import android.view.WindowManager
import android.widget.AutoCompleteTextView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
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
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
        recyclerView.layoutManager =
            CustomLinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
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

        dialogBinding = CrearRecetaBinding.inflate(layoutInflater)

        val builder = MaterialAlertDialogBuilder(this)
            .setTitle("Crea tu propia receta")
            .setMessage("Mensaje de receta")
            .setView(dialogBinding.root)
            .setNegativeButton("Cancelar") { dialog, which ->
                Toast.makeText(this, "Creación de receta cancelada", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setPositiveButton("Aceptar") { dialog, which ->
                Toast.makeText(
                    this,
                    "Esto añadirá la receta a la lista de recetas. Función en construcción",
                    Toast.LENGTH_SHORT
                ).show()
            }

        val dialog = builder.create() // Crear el diálogo
        dialog.show() // Mostrar el diálogo

        val window = dialog.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )

        val seekBar = dialogBinding.sbTiempo
        val textView = dialogBinding.tvValorSeekBar

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
            }


            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
    }
}