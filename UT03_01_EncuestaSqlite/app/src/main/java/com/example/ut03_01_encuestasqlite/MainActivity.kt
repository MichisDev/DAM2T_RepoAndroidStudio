package com.example.ut03_01_encuestasqlite

import adaptador.VistaAdaptador
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

import com.example.ut03_01_encuestasqlite.databinding.ActivityMainBinding
import modelo.Encuesta

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var encuestas: MutableList<Encuesta>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout con el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar la lista de encuestas
        encuestas = mutableListOf()

        // Configurar los listeners de los botones
        binding.btValidar.setOnClickListener {
            validarEncuesta()
        }
        binding.btCuantas.setOnClickListener {
            cuantasEncuestas()
        }
        binding.btResumen.setOnClickListener {
            resumenEncuestas()
        }
        binding.btBorrar.setOnClickListener {
            borrarFormulario()
        }
        binding.btReiniciar.setOnClickListener {
            reiniciarEncuestas()
        }
    }

    private fun reiniciarEncuestas() {
        // salir
        finish()
        // reiniciar
        startActivity(intent)
    }

    private fun borrarFormulario() {

        // Borra el fomulario
        binding.tvNombre.text?.clear()
        binding.swAnonimo.isChecked = false
        binding.rgSO.clearCheck()
        binding.sbHoras.progress = 0
        binding.ckDaw.isChecked = false
        binding.ckDam.isChecked = false
        binding.ckAsir.isChecked = false

        Toast.makeText(this, "Formulario borrado.", Toast.LENGTH_SHORT).show()
    }

    private fun resumenEncuestas() {
        // Crear objeto intent
        val intent = Intent(this, Detalle::class.java)

        // Añadir la encuesta a la intent
        intent.putExtra("encuesta", encuestas as java.io.Serializable)

        // Iniciar la activity
        startActivity(intent)
    }

    private fun cuantasEncuestas() {

        // Mostrar un alert dialog con el número de encuestas
        AlertDialog.Builder(this)
            .setTitle("Número de encuestas")
            .setMessage("Has realizado ${encuestas.size} encuestas")
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun validarEncuesta() {

        // Obtener los datos de la encuesta
        val nombre = binding.tvNombre.text.toString()
        val anonimo = binding.swAnonimo.isChecked
        val so = obtenerSOSeleccionado()
        val especialidad = obtenerEspecialidadesSeleccionadas()
        val horas = binding.sbHoras.progress

        // Validar nombre y anonimo vacios
        if (nombre.isEmpty() || (anonimo && nombre.isEmpty())) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear un objeto encuesta
        val nuevaEncuesta = Encuesta(nombre, anonimo, so, especialidad, horas)

        // Añadir la encuesta a la lista
        encuestas.add(nuevaEncuesta)

        // Mostrar un mensaje de éxito
        Toast.makeText(this, "Encuesta validada correctamente", Toast.LENGTH_SHORT).show()


    }

    private fun obtenerEspecialidadesSeleccionadas(): List<String> {
        val especialidadesSeleccionadas = mutableListOf<String>()
        if (binding.ckDaw.isChecked) {
            especialidadesSeleccionadas.add("DAW")
        }
        if (binding.ckDam.isChecked) {
            especialidadesSeleccionadas.add("DAM")
        }
        if (binding.ckAsir.isChecked) {
            especialidadesSeleccionadas.add("ASIR")

        }
        return especialidadesSeleccionadas
    }

    private fun obtenerSOSeleccionado(): String {
        val selectedId = binding.rgSO.checkedRadioButtonId
        val selectedRadioButton = binding.rgSO.findViewById<RadioButton>(selectedId)
        return selectedRadioButton?.text.toString()

    }


}