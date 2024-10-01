package com.example.pmdm_ut01_formulario

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los EditTexts
        val ptNombre = findViewById<EditText>(R.id.ptNombre)
        val ptApellido1 = findViewById<EditText>(R.id.ptApellido1)
        val ptApellido2 = findViewById<EditText>(R.id.ptApellido2)
        val tpnTelefono = findViewById<EditText>(R.id.tpnTelefono)
        val tdFecha = findViewById<EditText>(R.id.tdFecha)
        val tmEmail = findViewById<EditText>(R.id.tmEmail)
        val tpsContrasenia = findViewById<EditText>(R.id.tpsContrasenia)
        val tpsValContrasenia = findViewById<EditText>(R.id.tpsValContrasenia)
        val tvObservaciones = findViewById<EditText>(R.id.tvObservaciones)

        // Botón de Borrar
        val btBorrar = findViewById<Button>(R.id.btBorrar)
        btBorrar.setOnClickListener {
            // Limpiar los campos
            ptNombre.text.clear()
            ptApellido1.text.clear()
            ptApellido2.text.clear()
            tpnTelefono.text.clear()
            tdFecha.text.clear()
            tmEmail.text.clear()
            tpsContrasenia.text.clear()
            tpsValContrasenia.text.clear()
            tvObservaciones.text.clear()
        }

        // Botón de Aceptar
        val btAceptar = findViewById<Button>(R.id.btAceptar)
        btAceptar.setOnClickListener {
            val nombre = ptNombre.text.toString()
            val apellido1 = ptApellido1.text.toString()
            val apellido2 = ptApellido2.text.toString()
            val telefono = tpnTelefono.text.toString()
            val fecha = tdFecha.text.toString()
            val email = tmEmail.text.toString()
            val contrasenia = tpsContrasenia.text.toString()
            val valContrasenia = tpsValContrasenia.text.toString()

            // Validaciones básicas
            if (nombre.isEmpty()) {
                mostrarError(ptNombre, "El nombre no puede estar vacío")
                return@setOnClickListener
            }
            if (apellido1.isEmpty()) {
                mostrarError(ptApellido1, "El primer apellido no puede estar vacío")
                return@setOnClickListener
            }
            if (telefono.length != 9 || !telefono.all { it.isDigit() }) {
                mostrarError(tpnTelefono, "El teléfono debe tener 9 dígitos")
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                mostrarError(tmEmail, "El email no es válido")
                return@setOnClickListener
            }
            if (contrasenia != valContrasenia) {
                mostrarError(tpsValContrasenia, "Las contraseñas no coinciden")
                return@setOnClickListener
            }

            // Si todas las validaciones son correctas, mostramos un mensaje con los datos
            Toast.makeText(
                this,
                "Formulario correcto: \nNombre: $nombre\nApellido: $apellido1 $apellido2\nTeléfono: $telefono\nEmail: $email",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    // Método para mostrar errores en los campos
    private fun mostrarError(campo: EditText, mensaje: String) {
        campo.error = mensaje
        campo.requestFocus()
    }
}