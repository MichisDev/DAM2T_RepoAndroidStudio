package com.example.ut01_tarea_04_calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ut01_tarea_04_calculadora.databinding.ActivityMainBinding
import modelo.Encuesta

class MainActivity : AppCompatActivity() {

    // Constante para el binding
    private lateinit var binding: ActivityMainBinding

    // Inicialización de la lista
    private val encuestas = mutableListOf<Encuesta>()

    // Referencias a los componentes de la UI
    private lateinit var eTNombre: EditText
    private lateinit var swAnonimo: Switch
    private lateinit var radioGroupSO: RadioGroup
    private lateinit var ckDam: CheckBox
    private lateinit var ckAsir: CheckBox
    private lateinit var ckDaw: CheckBox
    private lateinit var sbHoras: SeekBar
    private lateinit var btnValidar: Button
    private lateinit var btnReiniciar: Button
    private lateinit var btnCuantas: Button
    private lateinit var btnResumen: Button
    private lateinit var textViewResumen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // inicializamos el binding inflamos
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar referencias a la UI
        eTNombre = findViewById(R.id.eTNombre)
        swAnonimo = findViewById(R.id.swAnonimo)
        radioGroupSO = findViewById(R.id.radioGroupSO)
        ckDam = findViewById(R.id.ckDam)
        ckAsir = findViewById(R.id.ckAsir)
        ckDaw = findViewById(R.id.ckDaw)
        sbHoras = findViewById(R.id.sbHoras)
        btnValidar = findViewById(R.id.btValidar)
        btnReiniciar = findViewById(R.id.btReiniciar)
        btnCuantas = findViewById(R.id.btCuantas)
        btnResumen = findViewById(R.id.btResumen)
        textViewResumen = findViewById(R.id.textViewResumen)

        // Configurar el listener para el botón "Validar"
        btnValidar.setOnClickListener {
            validarEncuesta()
        }

        // Configurar otros botones (Reiniciar, Cuantas, Resumen)
        btnReiniciar.setOnClickListener { reiniciarEncuesta() }
        btnCuantas.setOnClickListener { mostrarCantidadEncuestas() }
        btnResumen.setOnClickListener { mostrarResumen() }

        // Configurar el Switch para habilitar/deshabilitar el EditText
        swAnonimo.setOnCheckedChangeListener { _, isChecked ->
            eTNombre.isEnabled = !isChecked // Desactivar el EditText si está anónimo
            if (isChecked) {
                eTNombre.text.clear() // Limpiar el nombre si se activa anónimo
            }
        }

    }

    private fun validarEncuesta() {
        // Obtener los valores de los campos
        val nombre = if (swAnonimo.isChecked) "Anónimo" else eTNombre.text.toString()
        val sistemaOperativo = obtenerSOSeleccionado()
        val especialidadesSeleccionadas = obtenerEspecialidadesSeleccionadas()
        val horasEstudio = sbHoras.progress

        // Validar que los campos necesarios estén llenos
        if (!swAnonimo.isChecked && nombre.isEmpty()) {
            Toast.makeText(this, "Nombre necesario para validar.", Toast.LENGTH_SHORT).show()
            return
        }

        // Crear una nueva encuesta y agregarla a la lista
        val nuevaEncuesta = Encuesta(nombre, sistemaOperativo, especialidadesSeleccionadas, horasEstudio)
        encuestas.add(nuevaEncuesta)

        // Mostrar un mensaje de que la encuesta fue almacenada
        Toast.makeText(this, "Encuesta almacenada.", Toast.LENGTH_SHORT).show()

        // Ir a la siguiente actividad para mostrar detalles
        val intent = Intent(this, DetalleActivity::class.java)
        intent.putExtra("encuesta", nuevaEncuesta) // Usando Serializable
        startActivity(intent)
    }

    private fun obtenerSOSeleccionado(): String {
        val selectedId = radioGroupSO.checkedRadioButtonId
        val selectedRadioButton = findViewById<RadioButton>(selectedId)
        return selectedRadioButton?.text.toString()
    }

    private fun obtenerEspecialidadesSeleccionadas(): List<String> {
        val especialidades = mutableListOf<String>()
        if (ckDam.isChecked) especialidades.add(ckDam.text.toString())
        if (ckAsir.isChecked) especialidades.add(ckAsir.text.toString())
        if (ckDaw.isChecked) especialidades.add(ckDaw.text.toString())
        return especialidades
    }

    private fun reiniciarEncuesta() {
        // Reiniciar todos los campos
        eTNombre.text.clear()
        swAnonimo.isChecked = false
        radioGroupSO.clearCheck() // Limpiar la selección de radio buttons
        ckDam.isChecked = false
        ckAsir.isChecked = false
        ckDaw.isChecked = false
        sbHoras.progress = 4 // Reiniciar la barra de progreso a 4
        textViewResumen.text = "" // Limpiar el resumen

        // Limpiar la lista de encuestas
        encuestas.clear() // Esto borra todas las encuestas

        Toast.makeText(this, "Encuesta reiniciada.", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarCantidadEncuestas() {
        Toast.makeText(this, "Total de encuestas: ${encuestas.size}", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarResumen() {
        val resumen = StringBuilder()
        for (encuesta in encuestas) {
            resumen.append(encuesta.toString()).append("\n")
        }
        textViewResumen.text = resumen.toString()
    }
}