package com.example.ut01_04_encuestarecyclerview

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
import com.example.ut01_04_encuestarecyclerview.databinding.ActivityMainBinding
import modelo.Encuesta

class MainActivity : AppCompatActivity() {

    // Constante para el binding
    private lateinit var binding: ActivityMainBinding

    // Inicialización de la lista
    private val encuestas = mutableListOf<Encuesta>()

    // Referencias a los componentes de la UI
   private lateinit var eTNombre: EditText
   private lateinit var etwNombre: TextView
   private lateinit var swAnonimo: Switch
   private lateinit var radioGroupSO: RadioGroup
   private lateinit var ckDam: CheckBox
   private lateinit var ckAsir: CheckBox
   private lateinit var ckDaw: CheckBox
   private lateinit var sbHoras: SeekBar
   private lateinit var textViewResumen: TextView
   private lateinit var btnValidar: Button
   private lateinit var btnReiniciar: Button
   private lateinit var btnCuantas: Button
   private lateinit var btnResumen: Button
   private lateinit var btnBorrar: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar el layout con el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el comportamiento de Edge-to-Edge
        enableEdgeToEdge()

        // Inicializar referencias a la UI
        eTNombre = findViewById(R.id.eTNombre)
        etwNombre = findViewById(R.id.tvwNombre)
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
        btnBorrar = findViewById(R.id.btBorrar)



        // Inicializa el EditText como habilitado
        eTNombre.isEnabled = true

        // Configurar el listener para el botón "Validar"
        btnValidar.setOnClickListener {
            validarEncuesta()
        }

        // Configurar otros botones (Reiniciar, Cuantas, Resumen,Borrar)
        btnReiniciar.setOnClickListener { reiniciarEncuesta() }
        btnCuantas.setOnClickListener { mostrarCantidadEncuestas() }
        btnResumen.setOnClickListener { mostrarResumen() }
        btnBorrar.setOnClickListener { borrarFormulario() }


    }

    private fun borrarFormulario() {
        // Limpiar todos los campos del formulario
        eTNombre.text.clear()
        swAnonimo.isChecked = false
        radioGroupSO.clearCheck()
        ckDam.isChecked = false
        ckAsir.isChecked = false
        ckDaw.isChecked = false
        sbHoras.progress = 4 // Puedes establecer el valor que consideres predeterminado

        Toast.makeText(this, "Formulario borrado.", Toast.LENGTH_SHORT).show()
    }

    private fun mostrarResumen() {
        val intent = Intent(this, DetalleActivity::class.java)
        // Debes pasar la lista de encuestas al intent
        // Puedes usar putParcelableArrayListExtra si Encuesta implementa Parcelable
        // o usar putSerializable si Encuesta implementa Serializable
        intent.putExtra("encuestas", ArrayList(encuestas))
        startActivity(intent)
    }

    private fun mostrarCantidadEncuestas() {
        Toast.makeText(this, "Se han realizado ${encuestas.size} encuestas.", Toast.LENGTH_SHORT).show()
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
}