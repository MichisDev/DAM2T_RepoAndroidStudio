package com.example.pmdm_ut01_calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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


        // EditText
        val etnNumero1 = findViewById<EditText>(R.id.etnNumero1)
        val etnNumero2 = findViewById<EditText>(R.id.etnNumero2)
        val etnResultado = findViewById<EditText>(R.id.etnResultado)

        // Eventos de los botones
        btSumar.setOnClickListener {
           val num1 = etnNumero1.text.toString().toInt()
        }
        btRestar.setOnClickListener {   }
        btMultiplicar.setOnClickListener {   }
        btDividir.setOnClickListener {   }

    }
}