package com.example.pmdm_ut01_calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Botones
        val btSumar = findViewById<Button>(R.id.btSumar)
        val btRestar = findViewById<Button>(R.id.btRestar)
        val btMultiplicar = findViewById<Button>(R.id.btMultiplicar)
        val btDividir = findViewById<Button>(R.id.btDividir)
        val btBorrar = findViewById<Button>(R.id.btBorrar)


        // EditText
        val etnNumero1 = findViewById<EditText>(R.id.etnNumero1)
        val etnNumero2 = findViewById<EditText>(R.id.etnNumero2)

        // TesxtView
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val tvResto = findViewById<TextView>(R.id.tvResto)

        // Eventos de los botones
        btSumar.setOnClickListener {
            val num1 = etnNumero1.text.toString().toIntOrNull()
            val num2 = etnNumero2.text.toString().toIntOrNull()
            if (num1 != null && num2 != null) {
                val resultado = num1 + num2
                tvResultado.text = resultado.toString()
                val resto = num1 - num2
                tvResto.visibility = View.GONE // Ocultar resto en suma
            } else {
                Toast.makeText(this, "Por favor, introduce números válidos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btRestar.setOnClickListener {
            val num1 = etnNumero1.text.toString().toIntOrNull()
            val num2 = etnNumero2.text.toString().toIntOrNull()
            if (num1 != null && num2 != null) {
                val resultado = num1 - num2
                tvResultado.text = resultado.toString()
                tvResto.visibility = View.GONE // Ocultar resto en resta
            } else {
                Toast.makeText(this, "Por favor, introduce números válidos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btMultiplicar.setOnClickListener {
            val num1 = etnNumero1.text.toString().toIntOrNull()
            val num2 = etnNumero2.text.toString().toIntOrNull()
            if (num1 != null && num2 != null) {
                val resultado = num1 * num2
                tvResultado.text = resultado.toString()
                tvResto.visibility = View.GONE // Ocultar resto en resta
            } else {
                Toast.makeText(this, "Por favor, introduce números válidos", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        btDividir.setOnClickListener {
            val num1 = etnNumero1.text.toString().toIntOrNull()
            val num2 = etnNumero2.text.toString().toIntOrNull()

            if (num1 != null && num2 != null) {
                if (num2 != 0) {
                    val resultado = num1 / num2
                    tvResultado.text = resultado.toString()

                    // Mostrar el resto solo si hay
                    val resto = num1 % num2

                    if (resto != 0) {
                        tvResto.visibility = View.VISIBLE
                        tvResto.text = "$resto"
                    } else {
                        tvResto.visibility = View.GONE
                    }
                } else {
                    Toast.makeText(this, "Error: División por 0", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Introduce ambos números", Toast.LENGTH_SHORT).show()
            }

        }

        btBorrar.setOnClickListener {
            // Borrar el contenido de los EditText
            etnNumero1.text.clear()
            etnNumero2.text.clear()

            // Borrar el contenido de los TextView
            tvResultado.text = ""
            tvResto.text = ""

            // Mensaje de confirmación con Toast
            Toast.makeText(this, "Campos borrados", Toast.LENGTH_SHORT).show()
        }
    }
}