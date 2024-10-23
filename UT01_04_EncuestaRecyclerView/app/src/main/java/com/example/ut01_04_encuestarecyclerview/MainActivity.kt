package com.example.ut01_04_encuestarecyclerview

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.ut01_04_encuestarecyclerview.databinding.ActivityMainBinding
import modelo.Encuesta

class MainActivity : AppCompatActivity() {

    // Constante para el binding
    private lateinit var binding: ActivityMainBinding

    // Inicialización de la lista de encuestas
    private val encuestas = mutableListOf<Encuesta>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout con el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el comportamiento de Edge-to-Edge
        enableEdgeToEdge()

        // Configurar el listener para el botón "Validar"
        binding.btValidar.setOnClickListener {
            validarEncuesta()
        }

        // Configurar otros botones (Reiniciar, Cuantas, Resumen, Borrar)
        binding.btReiniciar.setOnClickListener { reiniciarEncuesta() }
        binding.btCuantas.setOnClickListener { mostrarCantidadEncuestas() }
        binding.btResumen.setOnClickListener { mostrarResumen() }
        binding.btBorrar.setOnClickListener { borrarFormulario() }

        // Configurar el Switch para habilitar/deshabilitar el EditText
        binding.swAnonimo.setOnCheckedChangeListener { _, isChecked ->
            binding.eTNombre.isEnabled = !isChecked // Desactivar si anónimo
            if (isChecked) {
                binding.eTNombre.text.clear() // Limpiar nombre si anónimo
            }
        }

        // Actualizar el TextView de horasEstudio al cambiar la SeekBar
        binding.sbHoras.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvHorasEstudio.text = getString(R.string.tvHorasEstudio).replace("4", progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    // Borrar el formulario
    private fun borrarFormulario() {
        binding.eTNombre.text.clear()
        binding.swAnonimo.isChecked = false
        binding.radioGroupSO.clearCheck()
        binding.ckDam.isChecked = false
        binding.ckAsir.isChecked = false
        binding.ckDaw.isChecked = false
        binding.sbHoras.progress = 4

        Toast.makeText(this, "Formulario borrado.", Toast.LENGTH_SHORT).show()
    }

    // Mostrar el resumen en DetalleActivity
    private fun mostrarResumen() {
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("encuestas", ArrayList(encuestas))
        startActivity(intent)
    }

    // Mostrar la cantidad de encuestas realizadas
    private fun mostrarCantidadEncuestas() {
        Toast.makeText(this, "Se han realizado ${encuestas.size} encuestas.", Toast.LENGTH_SHORT).show()
    }

    // Reiniciar la encuesta
    private fun reiniciarEncuesta() {
        binding.eTNombre.text.clear()
        binding.swAnonimo.isChecked = false
        binding.radioGroupSO.clearCheck()
        binding.ckDam.isChecked = false
        binding.ckAsir.isChecked = false
        binding.ckDaw.isChecked = false
        binding.sbHoras.progress = 4

        encuestas.clear()

        Toast.makeText(this, "Encuesta reiniciada.", Toast.LENGTH_SHORT).show()
    }

    // Validar la encuesta
    private fun validarEncuesta() {
        val nombre = if (binding.swAnonimo.isChecked) "Anónimo" else binding.eTNombre.text.toString()
        val sistemaOperativo = obtenerSOSeleccionado()
        val especialidadesSeleccionadas = obtenerEspecialidadesSeleccionadas()
        val horasEstudio = binding.sbHoras.progress

        if (!binding.swAnonimo.isChecked && nombre.isEmpty()) {
            Toast.makeText(this, "Nombre necesario para validar.", Toast.LENGTH_SHORT).show()
            return
        }

        val nuevaEncuesta = Encuesta(nombre, sistemaOperativo, especialidadesSeleccionadas, horasEstudio)
        encuestas.add(nuevaEncuesta)

        Toast.makeText(this, "Encuesta almacenada.", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("encuesta", nuevaEncuesta)
        startActivity(intent)
    }

    // Obtener el sistema operativo seleccionado
    private fun obtenerSOSeleccionado(): String {
        val selectedId = binding.radioGroupSO.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedId)
        return selectedRadioButton?.text.toString()
    }

    // Obtener las especialidades seleccionadas
    private fun obtenerEspecialidadesSeleccionadas(): List<String> {
        val especialidades = mutableListOf<String>()
        if (binding.ckDam.isChecked) especialidades.add(binding.ckDam.text.toString())
        if (binding.ckAsir.isChecked) especialidades.add(binding.ckAsir.text.toString())
        if (binding.ckDaw.isChecked) especialidades.add(binding.ckDaw.text.toString())
        return especialidades
    }
}