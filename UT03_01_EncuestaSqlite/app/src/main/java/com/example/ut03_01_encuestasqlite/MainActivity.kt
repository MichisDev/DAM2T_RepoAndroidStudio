package com.example.ut03_01_encuestasqlite

import adaptador.VistaAdaptador
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import auxiliar.Conexion

import com.example.ut03_01_encuestasqlite.databinding.ActivityMainBinding
import conexion.AdminSQLiteConexion
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

        // Configurar el listener para el sw anonimo
        binding.swAnonimo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.tvNombre.text?.clear()  // Limpia el campo de nombre si se activa el modo anónimo
                binding.tvNombre.isEnabled = false
                binding.tvNombre.inputType =
                    android.text.InputType.TYPE_NULL // Desactiva el teclado
            } else {
                binding.tvNombre.isEnabled = true
                binding.tvNombre.inputType =
                    android.text.InputType.TYPE_CLASS_TEXT // Restaura el teclado
            }
        }

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
        var nombre = binding.tvNombre.text.toString()
        val anonimo = binding.swAnonimo.isChecked
        val so = obtenerSOSeleccionado()
        val especialidades = obtenerEspecialidadesSeleccionadas()
        val horas = binding.sbHoras.progress

        // Validar nombre: Si el modo anónimo está activado, el nombre se considera como "Anónimo"
        if (anonimo) {
            nombre = "Anonimo" // Si está marcado como anónimo, el nombre se almacena como "Anonimo"
        }

        // Si el modo anónimo no está activado y el campo nombre está vacío, mostramos un mensaje de error
        if (!anonimo && nombre.isEmpty()) {
            Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear un objeto encuesta con los datos validados
        val nuevaEncuesta = Encuesta(nombre = nombre, SO = so, especialidad = especialidades, horasEstudio = horas)

        // Guardar la encuesta en la base de datos
        val dbHelper = AdminSQLiteConexion(this)
        val result = Conexion.insertarEncuesta(dbHelper, nuevaEncuesta)

        // Comprobar si la encuesta se insertó correctamente
        if (result != -1L) {
            // Añadir la encuesta a la lista en memoria (si se mantiene una lista de encuestas en la actividad)
            encuestas.add(nuevaEncuesta)

            // Mostrar un mensaje de éxito
            Toast.makeText(this, "Encuesta validada y almacenada correctamente", Toast.LENGTH_SHORT).show()
        } else {
            // Si hubo un error al insertar en la base de datos
            Toast.makeText(this, "Error al almacenar la encuesta", Toast.LENGTH_SHORT).show()
        }

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